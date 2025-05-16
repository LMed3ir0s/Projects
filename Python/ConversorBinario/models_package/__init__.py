print()
print ("=> Você importou", __name__)
print()

from .classes import Binario, Lista, Error, list_conv

def binari_converter(value):
    bin_object = Binario(value)
    if bin_object.check_num() is not None:
        bin_object.bin_gen()

def binari_list():
    Lista.listar_conv()