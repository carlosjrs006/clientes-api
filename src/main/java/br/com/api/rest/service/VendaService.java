package br.com.api.rest.service;

import br.com.api.rest.model.Produto;

import java.util.List;

public interface VendaService {

    List<Produto> getAllByFilterprodutos(String nomeProduto);
}
