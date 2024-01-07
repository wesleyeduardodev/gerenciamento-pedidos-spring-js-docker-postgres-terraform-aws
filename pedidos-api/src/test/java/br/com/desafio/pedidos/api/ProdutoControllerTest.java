package br.com.desafio.pedidos.api;
import br.com.desafio.pedidos.api.produto.ProdutoController;
import br.com.desafio.pedidos.api.produto.ProdutoFacade;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

    private MockMvc mockMvc;

    private static final String BASE_URL_PRODUTOS = "/api/produtos";

    @Mock
    private ProdutoFacade produtoFacade;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("Deve retornar produtos com sucesso.")
    void deveRetornarProdutosComSucesso() throws Exception {
        mockMvc.perform(get(BASE_URL_PRODUTOS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
