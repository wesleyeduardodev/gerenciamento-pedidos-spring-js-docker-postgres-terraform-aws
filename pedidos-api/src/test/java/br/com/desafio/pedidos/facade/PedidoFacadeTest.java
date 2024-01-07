package br.com.desafio.pedidos.facade;
import br.com.desafio.pedidos.api.pedido.PedidoFacade;
import br.com.desafio.pedidos.exceptions.NegocioException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class PedidoFacadeTest {

    @Autowired
    PedidoFacade pedidoFacade;

    @Test
    @DisplayName("Deve retornar erro negocial ao enviar uma lista vazia de pedidos.")
    public void deveRetornarErroNegocialAoEnviarUmaListaVaziaDePedidos() {
        Assertions.assertThrows(NegocioException.class, () -> pedidoFacade.criarPedidos(new ArrayList<>()));
    }
}

