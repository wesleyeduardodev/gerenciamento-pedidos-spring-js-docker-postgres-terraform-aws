package br.com.desafio.pedidos.service;
import br.com.desafio.pedidos.cache.CacheStore;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import br.com.desafio.pedidos.dto.FornecedorDTO;
import br.com.desafio.pedidos.dto.PrecoDTO;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import br.com.desafio.pedidos.integracao.FonecedorStrategy;
import br.com.desafio.pedidos.model.Fornecedor;
import br.com.desafio.pedidos.repository.FornecedorRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    CacheStore<Fornecedor> cacheFornecedor;

    @Autowired
    FonecedorStrategy fonecedorStrategy;

    public Fornecedor criarFornecedor(Fornecedor fornecedorParaSalvar) {
        Fornecedor fornecedorSalvo;
        Fornecedor fornecedorCache = cacheFornecedor.get(fornecedorParaSalvar.getCnpj());
        if (Objects.nonNull(fornecedorCache)) {
            fornecedorSalvo = fornecedorCache;
        } else {
            fornecedorSalvo = fornecedorRepository.findByCnpj(fornecedorParaSalvar.getCnpj()).orElse(null);
            if (Objects.isNull(fornecedorSalvo)) {
                fornecedorSalvo = fornecedorRepository.save(fornecedorParaSalvar);
            }
            cacheFornecedor.add(fornecedorSalvo.getCnpj(), fornecedorSalvo);
        }
        return fornecedorSalvo;
    }

    public FornecedorDTO buscarMelhorFornecedorParaUmPedido(PedidoRequest pedidoRequest) throws IndisponivelException {
        List<FornecedorDTO> fornecedoresProduto = fonecedorStrategy.buscarFornecedores(pedidoRequest.getGtin());
        FornecedorDTO melhorFornecedorParaOProduto = null;
        if (CollectionUtils.isNotEmpty(fornecedoresProduto)) {
            melhorFornecedorParaOProduto = buscarMelhorFornecedorComBaseNaQuantidadeMinimaDoPedido(pedidoRequest.getQuantidade(), fornecedoresProduto);
        }
        return melhorFornecedorParaOProduto;
    }

    private FornecedorDTO buscarMelhorFornecedorComBaseNaQuantidadeMinimaDoPedido(Integer quantidadeMinima, List<FornecedorDTO> fornecedoresProduto) {
        FornecedorDTO melhorFornecedorElegivel = null;
        for (FornecedorDTO fornecedorIteracao : fornecedoresProduto) {
            removerPrecosQueNaoAtendemAQuantidadeMinimaSolicitadaNoPedido(quantidadeMinima, fornecedorIteracao.getPrecos());
            if (CollectionUtils.isNotEmpty(fornecedorIteracao.getPrecos())) {
                melhorFornecedorElegivel = trocarMelhorFornecedorElegivelCasoOProximoTenhaPrecoMaisBaixo(fornecedorIteracao, melhorFornecedorElegivel);
            }
        }
        return melhorFornecedorElegivel;
    }

    private FornecedorDTO trocarMelhorFornecedorElegivelCasoOProximoTenhaPrecoMaisBaixo(FornecedorDTO proximoFornecedor, FornecedorDTO melhorFornecedorElegivel) {
        if (Objects.isNull(melhorFornecedorElegivel) || proximoFornecedor.getPrecoMaisBaixo().compareTo(melhorFornecedorElegivel.getPrecoMaisBaixo()) < 0) {
            melhorFornecedorElegivel = proximoFornecedor;
        }
        return melhorFornecedorElegivel;
    }

    private void removerPrecosQueNaoAtendemAQuantidadeMinimaSolicitadaNoPedido(Integer quantidadeMinimaSolicitada, List<PrecoDTO> precos) {
        precos.removeIf(preco -> quantidadeMinimaSolicitada < preco.getQuantidadeMinima());
    }
}
