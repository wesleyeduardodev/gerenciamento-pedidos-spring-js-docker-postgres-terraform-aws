package br.com.desafio.pedidos.api.produto;
import br.com.desafio.pedidos.dto.response.ProdutoResponse;
import br.com.desafio.pedidos.view.ErroView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping("/api/produtos")
public interface ProdutoAPI {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listas todos os produtos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))})
    })
    ResponseEntity<List<ProdutoResponse>> findAll();
}
