package br.com.fornecedores.produto.api.fornecedor;
import br.com.fornecedores.produto.dto.request.FornecedorProdutoRequest;
import br.com.fornecedores.produto.dto.response.FornecedorResponse;
import br.com.fornecedores.produto.exceptions.NaoEncontradoException;
import br.com.fornecedores.produto.model.FornecedorProduto;
import br.com.fornecedores.produto.service.FornecedorProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrecoFornecedorProdutoFacade {

    @Autowired
    private FornecedorProdutoService fornecedorProdutoService;

    public List<FornecedorResponse> findByGtin(String gtin) {
        List<FornecedorProduto> fornecedorProdutoList = fornecedorProdutoService.findByProduto(gtin);
        return fornecedorProdutoService.toResponseFromEntity(fornecedorProdutoList);
    }

    public void criarFornecedoresParaProduto(FornecedorProdutoRequest fornecedorProdutoRequest) throws NaoEncontradoException {
        fornecedorProdutoService.save(fornecedorProdutoRequest);
    }
}
