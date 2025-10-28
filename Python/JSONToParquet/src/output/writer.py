from pathlib import Path
from typing import Optional
from pyspark.sql import DataFrame, SparkSession
from pyspark.sql.utils import AnalysisException
from utils.logger import get_logger
import time

logger = get_logger(__name__)


class DataWriter:
    def __init__(self, spark: SparkSession):
        self.spark = spark
        self.logger = logger

    def _is_dataframe_valid(self, df: DataFrame) -> bool:
        if df is None:
            self.logger.error("DataFrame é None. Abortando escrita.")
            return False

        try:
            if df.rdd.isEmpty():
                self.logger.warning("DataFrame vazio. Nenhum arquivo será gerado.")
                return False
        except Exception as e:
            self.logger.error(f"Falha ao verificar se DataFrame está vazio: {e}")
            return False

        return True

    def _ensure_output_dir(self, output_path: str) -> Optional[Path]:
        if not output_path:
            self.logger.error("Caminho de saída inválido (vazio).")
            return None

        try:
            p = Path(output_path)
            p.mkdir(parents=True, exist_ok=True)
            return p
        except Exception as e:
            self.logger.error(f"Falha ao criar/validar diretório de saída '{output_path}': {e}")
            return None


    def write_parquet(
        self,
        df: DataFrame,
        output_path: str,
        repartition: Optional[int] = None,
        mode: str = "overwrite",
        compression: str = "snappy",
    ) -> bool:
        start_ts = time.time()

        if not self._is_dataframe_valid(df):
            return False

        base_path = self._ensure_output_dir(output_path)
        if not base_path:
            return False

        try:
            df_to_write = df
            if repartition and repartition > 0:
                self.logger.info(f"Reparticionando DataFrame para {repartition} partições antes da escrita.")
                df_to_write = df.repartition(repartition)

            # define opções de escrita
            write_opts = {"compression": compression}

            self.logger.info(f"Iniciando escrita Parquet em '{base_path}' — modo={mode}, compression={compression}")

            df_to_write.write.mode(mode).options(**write_opts).parquet(str(base_path))

            elapsed = time.time() - start_ts
            self.logger.info(f"Escrita concluída em {elapsed:.2f}s. Dados salvos em: {base_path}")
            return True

        except Exception as e:
            self.logger.error(f"Erro inesperado durante a escrita Parquet: {e}")
            return False
