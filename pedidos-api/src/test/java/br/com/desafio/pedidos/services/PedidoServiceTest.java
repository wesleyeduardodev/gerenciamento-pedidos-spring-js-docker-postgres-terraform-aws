package br.com.desafio.pedidos.services;
import br.com.desafio.pedidos.dto.PedidoDTO;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import br.com.desafio.pedidos.dto.response.PedidoResponse;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.exceptions.NegocioException;
import br.com.desafio.pedidos.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class PedidoServiceTest {

    @Value(value = "${path.resourse.json}")
    private String pathResourseJson;

    @Autowired
    PedidoService pedidoService;

    private ObjectMapper mapper;

    private CollectionType collectionTypePedidoRequest;

    private CollectionType collectionTypePedidosResponse;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        collectionTypePedidoRequest = mapper.getTypeFactory().constructCollectionType(List.class, PedidoRequest.class);
        collectionTypePedidosResponse = mapper.getTypeFactory().constructCollectionType(List.class, PedidoDTO.class);
    }

    @Test
    @DisplayName("Deve retornar pedidos corretamente de acordo com pedidos solicitados.")
    public void deveRetornarPedidosCorretamenteDeAcordoComPedidosSolicitacao() throws NaoEncontradoException, IndisponivelException, NegocioException, IOException {

        List<PedidoRequest> pedidosRequest = mapper.readValue(new File(pathResourseJson.concat("pedidosRequest.json")), collectionTypePedidoRequest);
        PedidoResponse pedidoResponseFromPedidosRequest = pedidoService.criarPedidos(pedidosRequest);

        PedidoResponse pedidoResponseFromPedidosJson = PedidoResponse
                .builder()
                .pedidos(mapper.readValue(new File(pathResourseJson.concat("pedidosResponse.json")), collectionTypePedidosResponse))
                .build();

        Assertions.assertEquals(pedidoResponseFromPedidosRequest, pedidoResponseFromPedidosJson);
    }

    @Test
    @DisplayName("Deve retornar erro negocial ao não encontrar nenhum fornecedor para a quantidade solicitada do produto.")
    public void deveRetornarErroAoNaoEncontrarNenhumFornecedorParaOProduto() {
        PedidoRequest pedidoRequest = PedidoRequest
                .builder()
                .gtin("7891000100103")
                .quantidade(2)
                .build();
        Assertions.assertThrows(NegocioException.class, () -> pedidoService.criarPedidos(Collections.singletonList(pedidoRequest)));
    }
}

