package br.com.fornecedores.produto.model;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fornecedor_produto")
public class FornecedorProduto {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @OneToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "fornecedorProduto", cascade = CascadeType.ALL)
    private List<PrecoFornecedorProduto> precosFornecedorProduto;
}
