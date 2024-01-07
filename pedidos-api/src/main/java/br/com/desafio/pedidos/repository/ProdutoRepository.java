package br.com.desafio.pedidos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.desafio.pedidos.model.Produto;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByGtin(String gtin);
}
