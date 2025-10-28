"""
Módulo responsável por configurar e retornar um logger padronizado.
"""

import logging
from typing import Optional


def get_logger(name: Optional[str] = None) -> logging.Logger:
    """
    Retorna um logger configurado com formato padrão e nível INFO.

    :param name: Nome do logger (geralmente __name__)
    :return: Instância de logger configurado
    """
    logger = logging.getLogger(name or "spark_project")

    if not logger.handlers:
        handler = logging.StreamHandler()
        formatter = logging.Formatter(
            fmt="%(asctime)s | %(levelname)-8s | %(name)s | %(message)s",
            datefmt="%Y-%m-%d %H:%M:%S"
        )
        handler.setFormatter(formatter)
        logger.addHandler(handler)
        logger.setLevel(logging.INFO)

    return logger