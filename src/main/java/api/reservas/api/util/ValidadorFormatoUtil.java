package api.reservas.api.util;

public class ValidadorFormatoUtil {
    public static boolean formatoCepValido(String cep) {
        if (cep == null || cep.isBlank()) {
            return false;
        }

        var cepTrim = somenteDigitos(cep);
        return cepTrim.length() == 8;
    }

    public static boolean formatoCnpjValido(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            return false;
        }

        var cnpjDigits = somenteDigitos(cnpj);
        return cnpjDigits.length() != 14;
    }

    public static String somenteDigitos(String texto) {
        return texto.replaceAll("\\D", "");
    }
}
