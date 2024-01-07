package br.com.fornecedores.produto.exeptionsmappers;
import br.com.fornecedores.produto.api.ApiResponse;
import br.com.fornecedores.produto.exceptions.AmbienteException;
import br.com.fornecedores.produto.view.ErroView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class AmbienteExceptionMapper {

    @ExceptionHandler(value = AmbienteException.class)
    public ResponseEntity<ErroView> toResponse(AmbienteException e) {
        return ApiResponse.internalError(e.getMessage());
    }
}
