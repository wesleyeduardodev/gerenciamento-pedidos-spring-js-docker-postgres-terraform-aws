package br.com.desafio.pedidos.mappers;
import br.com.desafio.pedidos.dto.PedidoDTO;
import br.com.desafio.pedidos.model.Pedido;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper
public interface PedidoMapper {
    List<Pedido> toEntityList(List<PedidoDTO> pedidos);
    List<PedidoDTO> toDtoList(List<Pedido> pedidos);
}
