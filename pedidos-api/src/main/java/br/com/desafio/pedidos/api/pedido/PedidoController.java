package br.com.desafio.pedidos.api.pedido;
import br.com.desafio.pedidos.api.ApiResponse;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.exceptions.NegocioException;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import br.com.desafio.pedidos.dto.response.PedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class PedidoController implements PedidoAPI {

    @Autowired
    private PedidoFacade pedidoFacade;

    @Override
    @RolesAllowed("admin")
    public ResponseEntity<PedidoResponse> findAll() {
        return ApiResponse.ok(pedidoFacade.findAll());
    }

    @RolesAllowed("admin")
    public ResponseEntity<PedidoResponse> criarPedidos(List<PedidoRequest> pedidosRequest) throws NegocioException, IndisponivelException, NaoEncontradoException {
        return ApiResponse.ok(pedidoFacade.criarPedidos(pedidosRequest));
    }
}
