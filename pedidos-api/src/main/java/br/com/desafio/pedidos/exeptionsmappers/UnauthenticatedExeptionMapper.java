package br.com.desafio.pedidos.exeptionsmappers;
import br.com.desafio.pedidos.api.ApiResponse;
import br.com.desafio.pedidos.exceptions.NegocioException;
import br.com.desafio.pedidos.exceptions.UnauthenticatedExeption;
import br.com.desafio.pedidos.view.ErroView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class UnauthenticatedExeptionMapper {

    @ExceptionHandler(value = UnauthenticatedExeption.class)
    public ResponseEntity<ErroView> toResponse(NegocioException e) {
        return ApiResponse.unauthorized(e.getMessage());
    }
}
