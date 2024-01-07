package br.com.desafio.pedidos.dto.response;
import br.com.desafio.pedidos.dto.PedidoDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(of = "pedidos")
public class PedidoResponse {
    private List<PedidoDTO> pedidos;
}
