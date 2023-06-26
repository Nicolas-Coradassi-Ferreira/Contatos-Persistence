package br.com.nicoservices.contatospersistence.util;

public class TelefoneUtil {

    private TelefoneUtil(){}


    public static String desformatar(String telefone){
        return telefone.replaceAll("[\\s()+\\-]+", "");
    }
    public static String formatar(String telefone) {
        return switch (telefone.length()) {
            case 9 -> (telefone.substring(0, 5) + '-' + telefone.substring(5, 9));
            case 12 -> '(' + telefone.substring(0, 3) + ") " + telefone.substring(3, 8) + '-' + telefone.substring(8, 12);
            case 14 -> '+' + telefone.substring(0, 2) + " (" + telefone.substring(2, 5) + ") " + telefone.substring(5, 10) + '-' + telefone.substring(10, 14);
            default -> telefone;
        };
    }


}
