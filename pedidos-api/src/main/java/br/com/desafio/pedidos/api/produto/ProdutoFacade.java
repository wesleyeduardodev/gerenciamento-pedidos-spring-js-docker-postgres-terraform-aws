package br.com.desafio.pedidos.api.produto;
import br.com.desafio.pedidos.dto.response.ProdutoResponse;
import br.com.desafio.pedidos.mappers.ProdutoMapper;
import br.com.desafio.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoFacade {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    public List<ProdutoResponse> findAll() {
        return produtoMapper.map(produtoService.findAll());
    }
}
