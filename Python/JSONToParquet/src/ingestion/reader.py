import os
from pyspark.sql import SparkSession, DataFrame
from utils.logger import get_logger
from utils.schema import get_json_schema

logger = get_logger(__name__)

def read_json_data(spark: SparkSession, path: str) -> DataFrame:
    logger.info(f"Iniciando leitura do arquivo JSON em: {path}")

    # Valida o caminho antes de tentar leitura
    if not os.path.exists(path):
        logger.error(f"Caminho inválido ou inexistente: {path}")
        raise FileNotFoundError(f"O caminho especificado não existe: {path}")
    
    try:
        df = (
            spark.read
            .option("multiLine", True)
            .schema(get_json_schema())  # Schema definido no módulo utils/schema.py
            .json(path)
        )
        total_registros = df.count()
        logger.info(f"Leitura concluída com sucesso. Total de registros: {total_registros}")

        # Ajusta número de partições conforme paralelismo padrão do Spark
        num_partitions = spark.sparkContext.defaultParallelism
        df = df.repartition(num_partitions)
        logger.info(f"DataFrame reparticionado dinamicamente em {num_partitions} partições.")

        return df

    except Exception as e:
        logger.error(f"Erro ao ler arquivo JSON: {e}")
        raise
