package br.com.desafio.pedidos.api.pedido;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.exceptions.NegocioException;
import br.com.desafio.pedidos.dto.request.PedidoRequest;
import br.com.desafio.pedidos.dto.response.PedidoResponse;
import br.com.desafio.pedidos.view.ErroView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/pedidos")
public interface PedidoAPI {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listas todos os pedidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))})
    })
    ResponseEntity<PedidoResponse> findAll();

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar novos pedidos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))})
    })
    ResponseEntity<PedidoResponse> criarPedidos(@Valid @RequestBody List<PedidoRequest> pedidosRequest) throws NegocioException, IndisponivelException, NaoEncontradoException;
}

