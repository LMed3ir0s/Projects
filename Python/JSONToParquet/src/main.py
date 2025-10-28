import sys
from pathlib import Path
import argparse
from config.spark_config import create_spark_session, stop_spark_session
from ingestion.reader import read_json_data
from transformation.normalizer import apply_normalize
from transformation.partitioning import apply_partitioning
from output.writer import DataWriter
from utils.logger import get_logger

logger = get_logger(__name__)


def run_pipeline(args):
    spark = create_spark_session("JsonToParquetPipeline", threads=4)
    df = None
    
    try:
        # -------------------
        # STEP 1: INGEST
        # -------------------
        if "ingest" in args.steps or "all" in args.steps:
            df = read_json_data(spark, args.input)
            if df is None or df.rdd.isEmpty():
                logger.warning("DataFrame vazio após ingestão. Pipeline abortado.")
                return
            logger.info(f"[STEP INGEST] Total de registros: {df.count()}")

        # -------------------
        # STEP 2: NORMALIZE
        # -------------------
        if "normalize" in args.steps or "all" in args.steps:
            df = apply_normalize(spark, df, args.schema)
            logger.info(f"[STEP NORMALIZE] Total de registros após normalização: {df.count()}")

        # -------------------
        # STEP 3: PARTITION
        # -------------------
        if "partition" in args.steps or "all" in args.steps:
            df = apply_partitioning(df, args.partition_cols)
            logger.info(f"[STEP PARTITION] DataFrame particionado.")

        # -------------------
        # STEP 4: WRITE
        # -------------------
        if "write" in args.steps or "all" in args.steps:
            writer = DataWriter(spark)
            success = writer.write_parquet(
                df,
                output_path=args.output,
                repartition=args.repartition,
                mode=args.mode
            )
            if not success:
                logger.error("Falha na escrita do DataFrame. Pipeline abortado.")
                return
            logger.info(f"[STEP WRITE] Pipeline concluído. Dados gravados em: {args.output}")

    except Exception as e:
        logger.error(f"Erro inesperado no pipeline: {e}", exc_info=True)
    finally:
        stop_spark_session(spark)


# -------------------------------
# CLI com argparse
# -------------------------------
def parse_args():
    parser = argparse.ArgumentParser(description="Pipeline PySpark JSON → Parquet")

    # Paths default para estrutura local do projeto
    parser.add_argument("--input", default="data/raw", help="Caminho do JSON de entrada (default=data/raw)")
    parser.add_argument("--output", default="data/processed", help="Diretório base de saída para Parquets (default=data/processed)")
    parser.add_argument("--schema", default="default", help="Nome do schema definido em utils/schema.py (default=default)")

    # Pipeline steps
    parser.add_argument("--steps", nargs="+", default=["all"],
                        help="Etapas a executar: ingest normalize partition write (default=all)")
    
    # Write
    parser.add_argument("--mode", default="overwrite",
                        help="Modo de escrita Parquet: overwrite, append, ignore, errorifexists (default=overwrite)")
    parser.add_argument("--repartition", type=int, default=1, help="Número de partições para escrever")

    # Colunas opcionais para particionamento lógico
    parser.add_argument("--partition_cols", nargs="+", default=None, help="Colunas para particionamento lógico")

    return parser.parse_args()


# -------------------------------
# Entry point
# -------------------------------
if __name__ == "__main__":
    args = parse_args()
    run_pipeline(args)
