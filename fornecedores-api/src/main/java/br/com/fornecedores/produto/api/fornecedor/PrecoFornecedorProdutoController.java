package br.com.fornecedores.produto.api.fornecedor;
import br.com.fornecedores.produto.api.ApiResponse;
import br.com.fornecedores.produto.dto.request.FornecedorProdutoRequest;
import br.com.fornecedores.produto.dto.response.FornecedorResponse;
import br.com.fornecedores.produto.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrecoFornecedorProdutoController implements PrecoFornecedorProdutoAPI {

    @Autowired
    private PrecoFornecedorProdutoFacade fornecedorProdutoFacade;

    @Override
    public ResponseEntity<List<FornecedorResponse>> findByGtin(String gtin) {
        List<FornecedorResponse> response = fornecedorProdutoFacade.findByGtin(gtin);
        return ApiResponse.ok(response);
    }

    @Override
    public ResponseEntity<?> criarFornecedores(List<FornecedorProdutoRequest> precoFornecedorProdutoRequest) throws NaoEncontradoException {
        for (FornecedorProdutoRequest fornecedorProdutoRequest : precoFornecedorProdutoRequest) {
            fornecedorProdutoFacade.criarFornecedoresParaProduto(fornecedorProdutoRequest);
        }
        return ResponseEntity.ok().build();
    }
}
