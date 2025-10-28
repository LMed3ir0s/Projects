from pyspark.sql import Row
from pyspark.sql import DataFrame, SparkSession
from pyspark.sql.functions import col, trim, lower, when, lit
from pyspark.sql.types import StringType
from utils.logger import get_logger
from utils.schema import get_json_schema
from pyspark.sql.utils import AnalysisException
from typing import Iterator, Dict, Any

logger = get_logger(__name__)


def _normalize_partition(rows: Iterator[Row], schema_fields: list) -> Iterator[Dict[str, Any]]:

    for row in rows:
        row_dict = row.asDict()  # converte Row para dict
        normalized_row = {}

        for field in schema_fields:
            name = field.name
            dtype = str(field.dataType).lower()

            # Acessa o valor via dict
            value = row_dict.get(name, None)

            # => String
            if "string" in dtype:
                normalized_row[name] = str(value).strip().lower() if value is not None else ""
                continue

            # => Inteiros
            if "int" in dtype or "long" in dtype:
                try:
                    normalized_row[name] = int(value) if value is not None else 0
                except Exception:
                    normalized_row[name] = 0
                continue

            # => Float / Double / Decimal
            if "float" in dtype or "double" in dtype or "decimal" in dtype:
                try:
                    normalized_row[name] = float(value) if value is not None else 0.0
                except Exception:
                    normalized_row[name] = 0.0
                continue

            # => Outros tipos mantidos como estão
            normalized_row[name] = value if value is not None else None

        yield normalized_row  # yield ao invés de append para eficiência


def normalize_dataframe(df: DataFrame, schema_name: str) -> DataFrame:
    try:
        schema = get_json_schema(schema_name)
        logger.info(f"Iniciando normalização do DataFrame com schema '{schema_name}'")

        # Padroniza nomes de colunas
        for old_name in df.columns:
            new_name = old_name.strip().lower().replace(" ", "_")
            if old_name != new_name:
                df = df.withColumnRenamed(old_name, new_name)

        # Limpeza e tratamento de nulos
        for field in schema.fields:
            if field.name not in df.columns:
                continue

            # String: trim + lower + default ""
            if isinstance(field.dataType, StringType):
                df = df.withColumn(
                    field.name,
                    when(col(field.name).isNotNull(), trim(lower(col(field.name))))
                    .otherwise(lit(""))
                )
                continue

            # Outros tipos: manter valor ou None
            df = df.withColumn(
                field.name,
                when(col(field.name).isNotNull(), col(field.name)).otherwise(lit(None))
            )

        # Conversão de tipos
        for field in schema.fields:
            if field.name in df.columns:
                df = df.withColumn(field.name, col(field.name).cast(field.dataType))

        logger.info("Normalização base concluída (schema + limpeza básica).")
        return df

    except AnalysisException as e:
        logger.error(f"Erro ao normalizar DataFrame: {e.desc}")
        raise
    except Exception as e:
        logger.error(f"Falha inesperada na normalização: {e}")
        raise


def apply_normalize(spark: SparkSession, df: DataFrame, schema_name: str) -> DataFrame:
    try:
        schema = get_json_schema(schema_name)
        logger.info(f"Aplicando normalização distribuída com mapPartitions para schema '{schema_name}'")

        # Converte para RDD e aplica normalização em partições
        normalized_rdd = df.rdd.mapPartitions(lambda rows: _normalize_partition(rows, schema.fields))

        # Reconstrói o DataFrame com o schema original
        normalized_df = spark.createDataFrame(normalized_rdd, schema=schema)
        logger.info("Normalização distribuída concluída com sucesso.")

        return normalized_df

    except Exception as e:
        logger.error(f"Erro ao aplicar normalização distribuída: {e}")
        raise
