package br.com.fornecedores.produto.repository;
import br.com.fornecedores.produto.model.FornecedorProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FornecedorProdutoRepository extends JpaRepository<FornecedorProduto, Long> {
    List<FornecedorProduto> findByProduto_Gtin(String gtin);
}
