package br.com.desafio.pedidos.integracao;
import br.com.desafio.pedidos.dto.FornecedorDTO;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@Profile({"dev","prod","dev-ide"})
public class FornecedorResource implements FonecedorStrategy {

    @Value(value = "${base.url.fornecedor}")
    private String baseUrlFornecedor;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<FornecedorDTO> buscarFornecedores(String gtin) throws IndisponivelException {
        try {
            String url = this.baseUrlFornecedor.concat(gtin);
            log.info("Acessando API fornecedores...");
            log.info("URL: " + url);
            List<FornecedorDTO> fornecedores = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, FornecedorDTO[].class)));
            log.info(CollectionUtils.size(fornecedores) + " fonecedor(es) encontrado(s) para o código de barras " + gtin);
            return fornecedores;
        } catch (Exception e) {
            String mensagem = "Erro ao buscar fornecedores para o código de barras " + gtin + "." + " Causa: " + e.getMessage();
            log.error(mensagem);
            throw new IndisponivelException(mensagem);
        }
    }
}
