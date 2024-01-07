package br.com.desafio.pedidos.repositories;
import br.com.desafio.pedidos.model.Fornecedor;
import br.com.desafio.pedidos.repository.FornecedorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import javax.persistence.EntityManager;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class FornecedorRepositotyTest {

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve criar um fornecedor.")
    void deveCriarUmFornecedor() {
        Fornecedor fornecedor = Fornecedor
                .builder()
                .cnpj("5456454564564654")
                .nome("Fornecedor 1")
                .build();
        fornecedor = fornecedorRepository.save(fornecedor);
        assertThat(Objects.nonNull(fornecedor.getId())).isTrue();
    }
}

