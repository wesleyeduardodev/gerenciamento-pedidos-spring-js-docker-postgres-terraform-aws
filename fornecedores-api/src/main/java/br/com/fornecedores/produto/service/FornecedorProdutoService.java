package br.com.fornecedores.produto.service;
import br.com.fornecedores.produto.dto.response.FornecedorResponse;
import br.com.fornecedores.produto.dto.PrecoDTO;
import br.com.fornecedores.produto.dto.request.FornecedorProdutoRequest;
import br.com.fornecedores.produto.exceptions.NaoEncontradoException;
import br.com.fornecedores.produto.model.Fornecedor;
import br.com.fornecedores.produto.model.FornecedorProduto;
import br.com.fornecedores.produto.model.PrecoFornecedorProduto;
import br.com.fornecedores.produto.model.Produto;
import br.com.fornecedores.produto.repository.FornecedorProdutoRepository;
import br.com.fornecedores.produto.repository.FornecedorRepository;
import br.com.fornecedores.produto.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FornecedorProdutoService {

    @Autowired
    private FornecedorProdutoRepository fornecedorProdutoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;


    public List<FornecedorProduto> findByProduto(String gtin) {
        return fornecedorProdutoRepository.findByProduto_Gtin(gtin);
    }

    public void save(FornecedorProdutoRequest fornecedorProdutoRequest) throws NaoEncontradoException {
        Optional<Produto> produto = produtoRepository.findByGtin(fornecedorProdutoRequest.getCodigoProduto());
        if (produto.isPresent()) {
            for (FornecedorResponse fornecedorDTO : fornecedorProdutoRequest.getFornecedores()) {
                Optional<Fornecedor> fornecedor = fornecedorRepository.findByCnpj(fornecedorDTO.getCnpj());
                if (fornecedor.isPresent()) {
                    FornecedorProduto fornecedorProduto = FornecedorProduto
                            .builder()
                            .produto(produto.get())
                            .fornecedor(fornecedor.get())
                            .build();
                    fornecedorProduto.setPrecosFornecedorProduto(toPrecosEntitiesFromPrecoDTOs(fornecedorProduto, fornecedorDTO.getPrecos()));
                    fornecedorProdutoRepository.save(fornecedorProduto);
                } else {
                    log.info("Fornecedor de cnpj " + fornecedorDTO.getCnpj() + " não encontrato ");
                    throw new NaoEncontradoException("Fornecedor de cnpj " + fornecedorDTO.getCnpj() + " não encontrato ");
                }
            }
        } else {
            log.info("Produto de código " + fornecedorProdutoRequest.getCodigoProduto() + " não encontrato ");
            throw new NaoEncontradoException("Produto de código " + fornecedorProdutoRequest.getCodigoProduto() + " não encontrato ");
        }
    }

    public List<PrecoFornecedorProduto> toPrecosEntitiesFromPrecoDTOs(FornecedorProduto fornecedorProduto, List<PrecoDTO> precoDTOS) {
        List<PrecoFornecedorProduto> precoFornecedorProdutoList = new ArrayList<>();
        precoDTOS.forEach(preco -> precoFornecedorProdutoList.add(PrecoFornecedorProduto
                .builder()
                .quantidadeMinima(preco.getQuantidadeMinima())
                .preco(preco.getPreco())
                .fornecedorProduto(fornecedorProduto)
                .build()));
        return precoFornecedorProdutoList;
    }

    public List<FornecedorResponse> toResponseFromEntity(List<FornecedorProduto> fornecedorProdutos) {
        List<FornecedorResponse> fornecedores = new ArrayList<>();
        fornecedorProdutos.forEach(fornecedorProduto -> {
            FornecedorResponse fornecedorDTO = FornecedorResponse
                    .builder()
                    .cnpj(fornecedorProduto.getFornecedor().getCnpj())
                    .nome(fornecedorProduto.getFornecedor().getNome())
                    .precos(toPrecosDTOsFromPrecoEntities(fornecedorProduto.getPrecosFornecedorProduto()))
                    .build();
            fornecedores.add(fornecedorDTO);

        });
        return fornecedores;
    }

    public List<PrecoDTO> toPrecosDTOsFromPrecoEntities(List<PrecoFornecedorProduto> precosFornecedorProduto) {
        List<PrecoDTO> precosDtos = new ArrayList<>();
        precosFornecedorProduto.forEach(preco -> precosDtos.add(PrecoDTO
                .builder()
                .preco(preco.getPreco())
                .quantidadeMinima(preco.getQuantidadeMinima())
                .build()));
        return precosDtos;
    }
}
