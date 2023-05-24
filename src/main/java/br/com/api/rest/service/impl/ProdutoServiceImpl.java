package br.com.api.rest.service.impl;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.model.Produto;
import br.com.api.rest.repository.ProdutoRepository;
import br.com.api.rest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto saveProduto(ProdutoRequestDto produtoRequestDto) {

        Produto produto =Produto.builder()
                .nomeProduto(produtoRequestDto.getNomeProduto())
                .valorProduto(produtoRequestDto.getValorProduto())
                .custoProduto(produtoRequestDto.getCustoProduto())
                .dataCadastro(formattedDate())
                .quantidade(produtoRequestDto.getQuantidade())
                .situacao(verifySituation(produtoRequestDto.getQuantidade()))
                .idImagem(produtoRequestDto.getIdImagem())
                .urlImagem(produtoRequestDto.getUrlImagem())
                .nomeImagem(produtoRequestDto.getNomeImagem())
                .build();
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> getAllProdutos() {
        return produtoRepository.getAllProdutos();
    }

    @Override
    public List<Produto> getAllByFilterprodutos(String situacao, String nomeProduto) {
        if (!StringUtils.isEmpty(situacao) && !StringUtils.isEmpty(nomeProduto)){
            return produtoRepository.getAllByFilterProdutos(situacao,nomeProduto);
        } else if(StringUtils.isEmpty(situacao) && !StringUtils.isEmpty(nomeProduto)){
            return produtoRepository.getAllByFilterNomeProdutos(nomeProduto);
        }else if(!StringUtils.isEmpty(situacao) && StringUtils.isEmpty(nomeProduto)){
            return produtoRepository.getAllByFilterSituacaoProdutos(situacao);
        }
        return List.of();
    }

    private String verifySituation(Integer quantidade) {

        if(quantidade.equals(0)){
            return "inativo";
        }
        return "ativo";
    }

    private String formattedDate(){
        Calendar backendDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(backendDate.getTime());
    }
}
