package br.com.desafio.pedidos.exeptionsmappers;
import br.com.desafio.pedidos.api.ApiResponse;
import br.com.desafio.pedidos.exceptions.NaoEncontradoException;
import br.com.desafio.pedidos.view.ErroView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class
NaoEncontradoExceptionMapper {

    @ExceptionHandler(value = NaoEncontradoException.class)
    public ResponseEntity<ErroView> toResponse(NaoEncontradoException e) {
        return ApiResponse.notFound(e.getMessage());
    }
}
