package br.com.desafio.pedidos.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(of = "id")
public class ProdutoResponse {
    private Long id;
    private String gtin;
    private String nome;
}
