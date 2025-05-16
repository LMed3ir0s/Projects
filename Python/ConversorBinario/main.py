#Seu objetivo é desenvolver um programa que permita ao usuário inserir um número  inteiro positivo e, 
# a partir disso, o programa deve converter esse número para sua forma binária, utilizando apenas lógica manual, sem o uso de funções prontas de conversão.
from models_package import binari_converter, binari_list
from utils import load_json, save_json
import os

load_json()
print()
print('Bem vindo ao Conversor de número para binário! 😄\n')
while True:

    user_input = input(
        'Escolha uma opção: \n'
        
        '1)✒️| Converter número.\n'
        '2)📋 | Listar números convertidos.\n' \
        '3)✋ | Sair.\n'
        )
    os.system('cls')
    print()

    if user_input == '1':
        user_input_2 = input('Digite um número inteiro positivo: \n')
        binari_converter(user_input_2)

    elif user_input == '2':
        os.system('cls')
        binari_list()
        continue

    elif user_input == '3':
        os.system('cls')
        save_json()
        print(f'👋 | Saindo do programa. Até logo!\n')
        break

    else:
        print('❌ | Opção inválida.')
        continue
    