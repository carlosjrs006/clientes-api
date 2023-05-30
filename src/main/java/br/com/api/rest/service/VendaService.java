package br.com.api.rest.service;

import br.com.api.rest.dto.VendaResquestDto;
import br.com.api.rest.model.Produto;
import br.com.api.rest.model.Venda;

import java.util.List;

public interface VendaService {

    List<Produto> getAllByFilterprodutos(String nomeProduto);

    Venda saveVenda(VendaResquestDto vendaResquestDto);
}
