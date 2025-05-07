list_conv = []

class Binario:
    def __init__(self, num_bin):
        self.num_bin = num_bin

    
    def check_num(self):
        print('\nChecando o número digitado...\n')
        try:
            num_int = int(self.num_bin)
            if num_int >= 0:
                print('Número válido!\nProcessando conversão...\n')
                print('Convertendo o número para sua forma binária...\n')
                return num_int
            
            else:
                Error.erro_num(self)
                return
        
        except ValueError:
            Error.erro_letra(self)
            return

    def bin_gen(self):
        num_bin = int(self.num_bin)
        user_input_0 = num_bin
        results = []

        while num_bin > 0:
            calc_bin = num_bin % 2
            results.append(calc_bin)
            num_bin = num_bin // 2
        results.reverse()
        msg = f'O número {user_input_0} convertido em binário é {results}'
        print(f'\n{msg}\n')
        list_conv.append(msg)
        return results
    
class Error:
    def __init__(self):
        pass

    def erro_letra(self):
        print('❌ | Erro! Você digitou algo diferente de um número.\n Por favor digite apenas números inteiros positivos.\n')

    def erro_num(self):
        print('❌ | Erro! Número Negativo identificado.\n Digite apenas números inteiros positivos.\n')
    

class Lista:
    def __init__(self):
        pass

    def listar_conv():
        print()
        if not list_conv:        
            print('Lista está vazia.\n')
            return
        print('Lista de conversões:')
        for conv in list_conv:
            print(f'\n{conv}')
        print()