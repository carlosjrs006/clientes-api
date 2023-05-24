package br.com.api.rest.service;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.model.Produto;

import java.util.List;

public interface ProdutoService {

    Produto saveProduto(ProdutoRequestDto produtoRequestDto);
    List<Produto> getAllByFilterprodutos(String situacao, String nomeProduto);
    List<Produto> getAllProdutos();
}
