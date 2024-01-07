package br.com.desafio.pedidos.utils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FormatadorUtilsTest {

    @Test
    @DisplayName("Deve remover caracteres não numéricos CNPJ.")
    public void deveRemoverCaracteresNaoNumericosCNPJ() {
        String cnpj = "42.217.933/0001-85";
        Assertions.assertTrue(StringUtils.isNumeric(FormatadorUtil.removerCaracteresNaoNumericos(cnpj)));
    }
}

