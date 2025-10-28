from pyspark.sql import SparkSession
import logging

def create_spark_session(app_name: str = "JsonToParquetPipeline", threads: int = 4):

    try:
        logging.info("Iniciando configuração da SparkSession...")

    # Configurações principais da sessão Spark
        spark = (
            SparkSession.builder
            .appName(app_name) 
            .master(f"local[{threads}]") # Define número de threads (ex: local[4] ou local[*])
            .config("spark.sql.shuffle.partitions", "8")  # Define número de partições usadas em operações de shuffle
            .config("spark.default.parallelism", "8")     # Paralelismo padrão para RDDs
            .config("spark.driver.memory", "4g")
            .config("spark.executor.memory", "4g")
            .getOrCreate()
        )
        spark.sparkContext.setLogLevel("WARN")
        logging.info("SparkSession criada com sucesso.")
        log_spark_config(spark)
        return spark

    except Exception as e:
        logging.error("Erro ao criar SparkSession: %s", e)
        raise

"""
Exibe e registra as principais configurações da SparkSession.

Args:
spark (SparkSession): Sessão Spark ativa.
"""
def log_spark_config(spark: SparkSession) -> None:
    try:
        conf = spark.sparkContext.getConf().getAll()
        logging.info("Configurações ativas da SparkSession:")
        for k, v in conf:
            logging.info(f"{k}: {v}")
    except Exception as e:
        logging.warning("Falha ao recuperar configurações da SparkSession: %s", e)


"""
Encerra a SparkSession de forma segura.

Args:
spark (SparkSession): Sessão Spark ativa.
"""
def stop_spark_session(spark: SparkSession) -> None:
    try:
        if spark is not None:
            spark.stop()
            logging.info("SparkSession encerrada com sucesso.")
    except Exception as e:
        logging.warning("Erro ao encerrar SparkSession: %s", e)
