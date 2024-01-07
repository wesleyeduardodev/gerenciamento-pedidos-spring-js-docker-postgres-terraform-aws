package br.com.desafio.pedidos.dto;
import br.com.desafio.pedidos.utils.FormatadorUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode(of = "cnpj")
public class FornecedorDTO {
    private String cnpj;
    private List<PrecoDTO> precos;
    private String nome;

    public BigDecimal getPrecoMaisBaixo() {
        return CollectionUtils.isNotEmpty(precos) ? precos.stream()
                .min(Comparator.comparing(PrecoDTO::getPreco))
                .get().getPreco() : null;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = FormatadorUtil.removerCaracteresNaoNumericos(cnpj);
    }
}
