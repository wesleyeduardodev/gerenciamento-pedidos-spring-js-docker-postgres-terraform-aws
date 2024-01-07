package br.com.desafio.pedidos.api.pedido;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import br.com.desafio.pedidos.dto.response.PedidoResponse;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.exceptions.NegocioException;
import br.com.desafio.pedidos.mappers.PedidoMapper;
import br.com.desafio.pedidos.service.PedidoService;
import br.com.desafio.pedidos.utils.ValidatorPedidoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoFacade {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoMapper pedidoMapper;

    public PedidoResponse findAll() {
        return PedidoResponse
                .builder()
                .pedidos(pedidoMapper.toDtoList(pedidoService.findAll()))
                .build();
    }

    public PedidoResponse criarPedidos(List<PedidoRequest> pedidosRequest) throws NegocioException, IndisponivelException, NaoEncontradoException {
        ValidatorPedidoRequest.validarTamanhoLista(pedidosRequest);
        return pedidoService.criarPedidos(pedidosRequest);
    }
}
