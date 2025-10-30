package banco.utils;


import javax.swing.*;
import java.util.Objects;

public class BancoUtils {


    // => Solicita Input
    public static String solicitaInput(String message) {
        BancoUtils.validaCampoObjeto(message, "MESSAGE não pode ser Nulo");
        return JOptionPane.showInputDialog(null, message);
    }

    // => Retorna Message View
    public static void messageView(String message){
        BancoUtils.validaCampoObjeto(message,"MESSAGE não pode ser Nulo");
        JOptionPane.showMessageDialog(null,message);
//        return message;
    }

    // => Valida Campo
    public static Object validaCampoObjeto(Object object, String nomeCampo) {
        var campoObjeto = Objects.requireNonNull(object, nomeCampo + " não pode ser Nulo");
        return campoObjeto;
    }


}



