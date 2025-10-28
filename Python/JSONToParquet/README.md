# Fluxo de Dados

```
┌────────────────────┐
│    JSON Raw Data   │
│ (data/raw/*.json)  │
└─────────┬──────────┘
          │ 
          ▼
┌────────────────────┐
│ PySpark DataFrame  │
│ (Estrutura bruta)  │
└─────────┬──────────┘
          │ 
          ▼
┌────────────────────┐
│ Normalização de    │
│ Colunas e Tipos    │
│ mapPartitions()    │
└─────────┬──────────┘
          │  
          ▼
┌────────────────────┐
│ Salva em Parquet   │
│ (data/processed/)  │
└─────────┬──────────┘
          │  
          ▼
┌────────────────────┐
│ ElasticSearch      │
│ (Indexação local)  │
└────────────────────┘
```
