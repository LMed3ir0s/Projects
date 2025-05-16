import json
import os
from models_package import list_conv

DATA_DIR = 'Bin_Converter/data'
DB_JSON = os.path.join(DATA_DIR, 'conversion_historic.json')


def load_json():
    if os.path.exists(DB_JSON):
        print(f'=> Carregando {DB_JSON}...')
        try:
            with open(DB_JSON, 'r', encoding='utf-8') as archive:
                database = json.load(archive)
                if isinstance(database, list):
                    list_conv.extend(database)

        except Exception as e:
            print(f"⚠️ | Erro ao carregar histórico: {e}")       

def save_json():
    print(f'\n=> Salvando Histórico de Conversões em {DB_JSON}...\n')
    try:
        with open(DB_JSON, 'w', encoding='utf-8') as archive:
            json.dump(list_conv, archive, ensure_ascii=False, indent=4)
    
    except Exception as e:
        print(f"⚠️ | Erro ao carregar histórico: {e}")
