package br.com.desafio.pedidos.service;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.model.Produto;
import br.com.desafio.pedidos.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto buscar(String gtin) throws NaoEncontradoException {
        return produtoRepository.findByGtin(gtin)
                .orElseThrow(() -> new NaoEncontradoException("Produto com código de barras " + gtin + " não encontrado."));
    }
}
