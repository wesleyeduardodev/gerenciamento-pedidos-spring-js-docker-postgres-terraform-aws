package br.com.desafio.pedidos.service;
import br.com.desafio.pedidos.dto.PedidoDTO;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.exceptions.NegocioException;
import br.com.desafio.pedidos.mappers.PedidoMapper;
import br.com.desafio.pedidos.mappers.ProdutoMapper;
import br.com.desafio.pedidos.model.Pedido;
import br.com.desafio.pedidos.repository.PedidoRepository;
import br.com.desafio.pedidos.dto.ItemPedidoDTO;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import br.com.desafio.pedidos.dto.FornecedorDTO;
import br.com.desafio.pedidos.dto.response.PedidoResponse;
import br.com.desafio.pedidos.dto.response.ProdutoResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PedidoService {

    @Value(value = "${mensagem.erro.negocial.fornecedor}")
    private String mensagemErroNegocial;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ProdutoMapper produtoMapper;

    public List<Pedido> salvarPedidos(List<PedidoDTO> peditosDTOs) {
        List<Pedido> pedidosParaSalvar = pedidoMapper.toEntityList(peditosDTOs);
        carregarFornecedoresDeCadaPedido(pedidosParaSalvar);
        return pedidoRepository.saveAll(pedidosParaSalvar);
    }

    private void carregarFornecedoresDeCadaPedido(List<Pedido> pedidos) {
        pedidos.forEach(pedido -> pedido.setFornecedor(fornecedorService.criarFornecedor(pedido.getFornecedor())));
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public PedidoResponse criarPedidos(List<PedidoRequest> pedidosRequest) throws NegocioException, IndisponivelException, NaoEncontradoException {
        Map<FornecedorDTO, List<ItemPedidoDTO>> mapaAgrupamentoFornecedoresProduto = new HashMap<>();
        for (PedidoRequest pedidoRequest : pedidosRequest) {
            FornecedorDTO melhorFornecedorParaOPedido = fornecedorService.buscarMelhorFornecedorParaUmPedido(pedidoRequest);
            ProdutoResponse produto = produtoMapper.map(produtoService.buscar(pedidoRequest.getGtin()));
            if (Objects.nonNull(melhorFornecedorParaOPedido)) {
                List<ItemPedidoDTO> itensPedidoFornecedor = mapaAgrupamentoFornecedoresProduto.get(melhorFornecedorParaOPedido);
                if (CollectionUtils.isNotEmpty(itensPedidoFornecedor)) {
                    itensPedidoFornecedor.add(buildItemPedido(pedidoRequest, produto, melhorFornecedorParaOPedido));
                } else {
                    itensPedidoFornecedor = new ArrayList<>(Collections.singletonList(buildItemPedido(pedidoRequest, produto, melhorFornecedorParaOPedido)));
                    mapaAgrupamentoFornecedoresProduto.put(melhorFornecedorParaOPedido, itensPedidoFornecedor);
                }
            } else {
                throw new NegocioException(mensagemErroNegocial.concat(": " + produto.getNome()));
            }
        }
        return PedidoResponse
                .builder()
                .pedidos(pedidoMapper.toDtoList(salvarPedidos(converterMapaAgrupamentoFornecedoresProdutoEmListaDePedidos(mapaAgrupamentoFornecedoresProduto))))
                .build();
    }

    private List<PedidoDTO> converterMapaAgrupamentoFornecedoresProdutoEmListaDePedidos(Map<FornecedorDTO, List<ItemPedidoDTO>> mapaAgrupamentoFornecedorProduto) {
        List<PedidoDTO> pedidos = new ArrayList<>();
        for (Map.Entry<FornecedorDTO, List<ItemPedidoDTO>> agrupamentoFornecedoresProduto : mapaAgrupamentoFornecedorProduto.entrySet()) {
            pedidos.add(buildPedido(agrupamentoFornecedoresProduto));
        }
        return pedidos;
    }

    private ItemPedidoDTO buildItemPedido(PedidoRequest pedidoRequest, ProdutoResponse produto, FornecedorDTO fornecedorDTO) {
        return ItemPedidoDTO
                .builder()
                .produto(produto)
                .preco(fornecedorDTO.getPrecoMaisBaixo())
                .quantidade(pedidoRequest.getQuantidade())
                .build();
    }

    private PedidoDTO buildPedido(Map.Entry<FornecedorDTO, List<ItemPedidoDTO>> agruppamentoFornecedor) {
        return PedidoDTO
                .builder()
                .fornecedor(agruppamentoFornecedor.getKey())
                .itens(agruppamentoFornecedor.getValue())
                .build();
    }
}
