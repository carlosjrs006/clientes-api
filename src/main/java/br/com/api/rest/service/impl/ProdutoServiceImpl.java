package br.com.api.rest.service.impl;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.model.Cliente;
import br.com.api.rest.model.Produto;
import br.com.api.rest.repository.ProdutoRepository;
import br.com.api.rest.service.ProdutoService;
import br.com.api.rest.service.StorageCloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private StorageCloudinaryService storageCloudinaryService;

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

    @Override
    public void deleteProdutoById(Produto produto) throws IOException {
        if(Objects.nonNull(produto.getIdImagem())){
            storageCloudinaryService.delete(produto.getIdImagem());
        }
        produtoRepository.deleteById(produto.getCodProduto());
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public Produto getProdutoById(Long codProduto) {
        return produtoRepository.getById(codProduto);
    }

    @Override
    public Produto updateProdutoById(Produto produtoDto) {

        Produto produto =  Produto.builder()
            .codProduto(produtoDto.getCodProduto())
            .nomeProduto(produtoDto.getNomeProduto())
            .custoProduto(produtoDto.getCustoProduto())
            .valorProduto(produtoDto.getValorProduto())
            .situacao(verifySituation(produtoDto.getQuantidade()))
            .quantidade(produtoDto.getQuantidade())
            .idImagem(produtoDto.getIdImagem())
            .nomeImagem(produtoDto.getNomeImagem())
            .urlImagem(produtoDto.getUrlImagem())
                .dataEditada(formattedDate()).build();
        return produtoRepository.save(produto);
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
