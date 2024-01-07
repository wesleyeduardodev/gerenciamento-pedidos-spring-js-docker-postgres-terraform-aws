package br.com.desafio.pedidos.dto;
import br.com.desafio.pedidos.dto.response.ProdutoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(of = {"produto", "quantidade", "preco"})
public class ItemPedidoDTO {

    private ProdutoResponse produto;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal total;

    public BigDecimal getTotal() {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
