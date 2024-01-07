package br.com.desafio.pedidos.dto.request;
import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {

    @NotBlank(message = "O código de barras não pode estar vazio.")
    private String gtin;

    @NotNull(message = "O valor da quantidade não poder ser nula.")
    @Min(value = 0, message = "Não é permitido incluir quantidade abaixo de 0.")
    private Integer quantidade;
}
