package br.com.desafio.pedidos.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(of = {"preco", "quantidadeMinima"})
public class PrecoDTO {
    private BigDecimal preco;
    private Integer quantidadeMinima;
}
