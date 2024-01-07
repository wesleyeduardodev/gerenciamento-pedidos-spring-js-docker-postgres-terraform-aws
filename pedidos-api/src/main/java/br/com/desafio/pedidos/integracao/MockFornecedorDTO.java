package br.com.desafio.pedidos.integracao;
import br.com.desafio.pedidos.dto.FornecedorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MockFornecedorDTO {
    private String codigoProduto;
    private List<FornecedorDTO> fornecedores;
}
