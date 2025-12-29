# ⚙️ JSON to Parquet Converter

![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54)
![PySpark](https://img.shields.io/badge/pyspark-%23E25A1C.svg?style=for-the-badge&logo=apache-spark&logoColor=white)

Aplicação simples para **conversão de arquivos JSON em formato Parquet**, desenvolvida com **PySpark**.  
O objetivo é praticar conceitos de **processamento distribuído de dados** e **organização em camadas** dentro de um pipeline de ETL.

---

## 🧩 Estrutura do projeto

O fluxo é dividido em **três camadas principais**:

### 1️⃣ Ingestão
Realiza a leitura de um arquivo **JSON bruto** e cria o DataFrame inicial do PySpark.

### 2️⃣ Transformação
Normaliza e particiona os dados com base em colunas específicas do JSON, organizando-os em DataFrames separados.

### 3️⃣ Output
Grava os DataFrames transformados em formato **Parquet**, utilizando partições para otimizar consultas e armazenamento.

---

## 🛠️ Tecnologias

- **Python 3.10+**
- **Apache Spark / PySpark**
- **Parquet (Apache Arrow)**
- **JSON**

---