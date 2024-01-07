package br.com.fornecedores.produto.model;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "preco_fornecedor_produto")
public class PrecoFornecedorProduto {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "quantidade_minima", nullable = false)
    private Integer quantidadeMinima;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor_produto", nullable = false)
    private FornecedorProduto fornecedorProduto;
}
