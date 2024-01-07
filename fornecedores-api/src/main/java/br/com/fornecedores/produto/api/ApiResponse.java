package br.com.fornecedores.produto.api;
import br.com.fornecedores.produto.view.ErroView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    public static <T> ResponseEntity<T> ok(final T entity) {
        return ResponseEntity.ok().body(entity);
    }

    public static ResponseEntity<ErroView> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErroView.builder().mensagem(message).build());
    }

    public static ResponseEntity<ErroView> internalError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErroView.builder().mensagem(message).build());
    }

    public static ResponseEntity<ErroView> unavailable(String message) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ErroView.builder().mensagem(message).build());
    }

    public static ResponseEntity<ErroView> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErroView.builder().mensagem(message).build());
    }

    public static ResponseEntity<ErroView> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErroView.builder().mensagem(message).build());
    }

    public static ResponseEntity<ErroView> forbiden(String message) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErroView.builder().mensagem(message).build());
    }
}
