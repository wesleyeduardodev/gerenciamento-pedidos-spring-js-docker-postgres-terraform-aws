package br.com.desafio.pedidos.cache;
import br.com.desafio.pedidos.model.Fornecedor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheFornecedor {

    @Bean
    public CacheStore<Fornecedor> cacheFornecedores() {
        return new CacheStore<>(60, TimeUnit.MINUTES);
    }
}
