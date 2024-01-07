package br.com.desafio.pedidos.integracao;
import br.com.desafio.pedidos.dto.FornecedorDTO;
import br.com.desafio.pedidos.exceptions.IndisponivelException;
import java.util.List;

public interface FonecedorStrategy {
    List<FornecedorDTO> buscarFornecedores(String gtin) throws IndisponivelException;
}

