package br.com.desafio.pedidos.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(of = {"id", "fornecedor", "itens"})
public class PedidoDTO {
    private Long id;
    private FornecedorDTO fornecedor;
    private List<ItemPedidoDTO> itens;
}
