package br.com.api.rest.service.impl;

import br.com.api.rest.model.Produto;
import br.com.api.rest.repository.ProdutoRepository;
import br.com.api.rest.repository.VendaRepository;
import br.com.api.rest.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> getAllByFilterprodutos(String nomeProduto) {
        return produtoRepository.getAllByFilterNomeAndAtivoProdutos(nomeProduto,"ativo");
    }
}
