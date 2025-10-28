"""
Módulo de definição de schemas para DataFrames.

Permite múltiplos schemas nomeados para leitura consistente de arquivos JSON,
usando mapeamento em dicionário.
"""

from pyspark.sql.types import StructType, StructField, StringType, IntegerType, DoubleType, TimestampType

# Dicionário que armazena todos os schemas disponíveis
SCHEMAS = {
    "default": StructType([
        StructField("id", IntegerType(), True),
        StructField("nome", StringType(), True),
        StructField("categoria", StringType(), True),
        StructField("valor", DoubleType(), True),
        StructField("data_criacao", TimestampType(), True),
    ]),

    # Futuro schemas podem ser adicionados assim:
    # "novo_schema": StructType([
    #     StructField("campo1", StringType(), True),
    #     StructField("campo2", IntegerType(), True),
    # ]),
}

def get_json_schema(schema_name: str = "default") -> StructType:
    try:
        return SCHEMAS[schema_name]
    except KeyError:
        raise ValueError(f"Schema '{schema_name}' não encontrado")
