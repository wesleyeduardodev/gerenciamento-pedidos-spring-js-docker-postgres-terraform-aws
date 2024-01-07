package br.com.desafio.pedidos.integracao;
import br.com.desafio.pedidos.dto.FornecedorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Profile("test")
public class MockFornecedorResource implements FonecedorStrategy {

    @Value(value = "${path.resourse.json}")
    private String pathResourseJson;

    @Override
    public List<FornecedorDTO> buscarFornecedores(String gtin) {
        List<FornecedorDTO> fornecedores = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType collectionTypePedidoResponse = mapper.getTypeFactory().constructCollectionType(List.class, MockFornecedorDTO.class);
            List<MockFornecedorDTO> fakeFornecedoresProduto = mapper.readValue(new File(pathResourseJson.concat("mockFornecedoresProdutos.json")), collectionTypePedidoResponse);
            fakeFornecedoresProduto
                    .stream()
                    .filter(i -> i.getCodigoProduto().equalsIgnoreCase(gtin))
                    .forEach(i -> fornecedores.addAll(i.getFornecedores()));
        } catch (IOException e) {
            log.error("Erro ao ler arquivo json de fornecedores de produtos.");
            throw new RuntimeException(e);
        }
        return fornecedores;
    }
}
