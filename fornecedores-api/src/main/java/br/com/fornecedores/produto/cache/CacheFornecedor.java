package br.com.fornecedores.produto.cache;
import br.com.fornecedores.produto.model.Fornecedor;
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
