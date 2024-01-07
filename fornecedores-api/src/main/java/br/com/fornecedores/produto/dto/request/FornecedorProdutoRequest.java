package br.com.fornecedores.produto.dto.request;
import br.com.fornecedores.produto.dto.response.FornecedorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FornecedorProdutoRequest {
    private String codigoProduto;
    private List<FornecedorResponse> fornecedores;
}
