package br.com.api.rest.service;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.model.Produto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto saveProduto(ProdutoRequestDto produtoRequestDto);
    List<Produto> getAllByFilterprodutos(String situacao, String nomeProduto);
    List<Produto> getAllProdutos();

    void deleteProdutoById(Produto produto) throws IOException;
    Optional<Produto> findById(Long id);
    Produto getProdutoById(Long codProduto);
    Produto updateProdutoById(Produto produto);
}
