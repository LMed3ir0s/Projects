from pyspark.sql import DataFrame
from pyspark.sql.utils import AnalysisException
from utils.logger import get_logger

logger = get_logger(__name__)


def validate_partition_columns(df: DataFrame, partition_cols: list[str]) -> list[str]:
    valid_cols = [c for c in partition_cols if c in df.columns]
    invalid_cols = set(partition_cols) - set(valid_cols)
    if invalid_cols:
        logger.warning(f"Colunas inválidas ignoradas no particionamento: {invalid_cols}")
    return valid_cols


def apply_partitioning(df: DataFrame, partition_cols: list[str] | None = None) -> DataFrame:
    try:
        logger.info("Iniciando aplicação de particionamento.")

        # Validação básica do DataFrame
        if df.rdd.isEmpty():
            logger.warning("O DataFrame está vazio. Nenhum particionamento aplicado.")
            return df

        # Número de partições físicas definido pelo Spark (spark.sql.shuffle.partitions)
        repartition_num = int(df.sparkSession.conf.get("spark.sql.shuffle.partitions", "8"))
        if repartition_num > 0:
            logger.info(f"Aplicando repartition padrão com {repartition_num} partições.")
            df = df.repartition(repartition_num)

        # Aplicando particionamento lógico (por colunas)
        if partition_cols:
            valid_cols = validate_partition_columns(df, partition_cols)
            if not valid_cols:
                logger.warning("Nenhuma coluna válida encontrada para particionamento lógico.")
                return df
            logger.info(f"Aplicando particionamento lógico por colunas: {valid_cols}")
            df = df.repartition(*valid_cols)
            logger.info("Particionamento lógico concluído.")

        logger.info("Particionamento total concluído com sucesso.")
        return df

    except AnalysisException as e:
        logger.error(f"Erro de análise ao aplicar particionamento: {e.desc}")
        raise
    except Exception as e:
        logger.error(f"Falha inesperada ao aplicar particionamento: {e}")
        raise
