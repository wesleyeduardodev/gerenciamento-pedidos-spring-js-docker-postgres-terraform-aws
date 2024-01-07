package br.com.fornecedores.produto.utils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FormatadorUtil {

    public static String removerCaracteresNaoNumericos(String value) {
        return value.replaceAll("[^\\d ]", "");
    }
}
