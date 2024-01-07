package br.com.fornecedores.produto.api.fornecedor;
import br.com.fornecedores.produto.dto.request.FornecedorProdutoRequest;
import br.com.fornecedores.produto.dto.response.FornecedorResponse;
import br.com.fornecedores.produto.exceptions.IndisponivelException;
import br.com.fornecedores.produto.exceptions.NaoEncontradoException;
import br.com.fornecedores.produto.exceptions.NegocioException;
import br.com.fornecedores.produto.view.ErroView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RequestMapping("/api/fornecedores/produtos")
public interface PrecoFornecedorProdutoAPI {

    @GetMapping(value = "/{gtin}", produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Listas todos os fornecedores de um produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FornecedorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))})
    })
    ResponseEntity<List<FornecedorResponse>> findByGtin(@PathVariable
                                                         @NotBlank
                                                         @Size(min = 1, max = 20)
                                                         @Parameter(description = "Código obra do sistema parceiro de integração", required = true, schema = @Schema(type = "string"))
                                                         String gtin);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Criar fornecedores para um produto.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida",
                    content = @Content(mediaType = "application/json"
                    )),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden request", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErroView.class))})
    })
    ResponseEntity<?> criarFornecedores(@Valid @RequestBody List<FornecedorProdutoRequest> precoFornecedorProdutoRequest) throws NegocioException, IndisponivelException, NaoEncontradoException;
}

