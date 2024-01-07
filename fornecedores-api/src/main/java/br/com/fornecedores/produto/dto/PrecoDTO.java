package br.com.fornecedores.produto.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecoDTO {
    private BigDecimal preco;
    private Integer quantidadeMinima;
}
