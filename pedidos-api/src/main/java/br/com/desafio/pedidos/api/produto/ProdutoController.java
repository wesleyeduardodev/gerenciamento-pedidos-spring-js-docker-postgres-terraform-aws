package br.com.desafio.pedidos.api.produto;
import br.com.desafio.pedidos.api.ApiResponse;
import br.com.desafio.pedidos.dto.response.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class ProdutoController implements ProdutoAPI {

    @Autowired
    private ProdutoFacade produtoFacade;

    @Override
    @RolesAllowed("admin")
    public ResponseEntity<List<ProdutoResponse>> findAll() {
        return ApiResponse.ok(produtoFacade.findAll());
    }
}
