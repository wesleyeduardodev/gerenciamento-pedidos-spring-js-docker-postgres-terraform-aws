package br.com.desafio.pedidos.api;
import br.com.desafio.pedidos.api.pedido.PedidoController;
import br.com.desafio.pedidos.api.pedido.PedidoFacade;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    private MockMvc mockMvc;

    private static final String BASE_URL_PEDIDOS = "/api/pedidos";

    private ObjectMapper mapper;

    @Mock
    private PedidoFacade pedidoFacade;

    @InjectMocks
    private PedidoController pedidoController;


    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("Deve retornar pedidos com sucesso.")
    void deveRetornarPedidosComSucesso() throws Exception {
        mockMvc.perform(get(BASE_URL_PEDIDOS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve criar pedidos com sucesso.")
    void deveCriarPedidosComSucesso() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL_PEDIDOS)
                        .content(asJsonString(Collections.singletonList(PedidoRequest.builder().build())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
