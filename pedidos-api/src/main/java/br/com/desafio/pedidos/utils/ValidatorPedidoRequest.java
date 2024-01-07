package br.com.desafio.pedidos.utils;
import br.com.desafio.pedidos.exceptions.NegocioException;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import java.util.List;

@UtilityClass
public class ValidatorPedidoRequest {

    private static final String MENSAGEM_ERRO_NEGOCIAL_LISTA_PEDIDOS_VAZIA = "Eh necessario informar pelo menos 1 item com quantidade maior que 0.";

    public static void validarTamanhoLista(List<?> list) throws NegocioException {
        if (CollectionUtils.isEmpty(list)) {
            throw new NegocioException(MENSAGEM_ERRO_NEGOCIAL_LISTA_PEDIDOS_VAZIA);
        }
    }
}
