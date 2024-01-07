package br.com.desafio.pedidos.repositories;
import br.com.desafio.pedidos.model.Produto;
import br.com.desafio.pedidos.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProdutoRepositotyTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve encontrar um produto pelo código de barras.")
    void deveEncontrarProdutoPeloCodigoDeBarras() {
        String gtin = "7894900011517";
        Optional<Produto> produto = produtoRepository.findByGtin(gtin);
        assertThat(produto.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve criar um produto.")
    void deveCriarUmProduto() {
        Produto produto = Produto
                .builder()
                .nome("Produto Teste")
                .gtin("6265556")
                .build();
        produto = produtoRepository.save(produto);
        assertThat(Objects.nonNull(produto.getId())).isTrue();
    }

    @Test
    @DisplayName("Não deve encontrar um produto pelo código de barras.")
    void naoDeveEncontrarProdutoPeloCodigoDeBarras() {
        Optional<Produto> produto = produtoRepository.findByGtin(UUID.randomUUID().toString());
        assertThat(produto.isEmpty()).isTrue();
    }
}

