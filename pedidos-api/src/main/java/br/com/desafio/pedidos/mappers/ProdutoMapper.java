package br.com.desafio.pedidos.mappers;
import br.com.desafio.pedidos.dto.response.ProdutoResponse;
import br.com.desafio.pedidos.model.Produto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface ProdutoMapper {
    ProdutoResponse map(Produto produto);
    List<ProdutoResponse> map(List<Produto> produtos);
}
