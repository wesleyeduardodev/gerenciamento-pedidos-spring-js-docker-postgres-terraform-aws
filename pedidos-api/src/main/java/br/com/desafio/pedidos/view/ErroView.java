package br.com.desafio.pedidos.view;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErroView {
    private Integer codigo;
    private String mensagem;
}
