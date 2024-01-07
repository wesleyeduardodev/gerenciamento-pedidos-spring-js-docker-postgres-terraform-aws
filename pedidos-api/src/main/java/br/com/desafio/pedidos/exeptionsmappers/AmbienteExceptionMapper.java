package br.com.desafio.pedidos.exeptionsmappers;
import br.com.desafio.pedidos.api.ApiResponse;
import br.com.desafio.pedidos.exceptions.AmbienteException;
import br.com.desafio.pedidos.view.ErroView;
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
