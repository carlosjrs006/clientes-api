package br.com.api.rest.controller;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.model.Produto;
import br.com.api.rest.service.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping("")
    public ResponseEntity<Produto> saveCliente(@RequestBody ProdutoRequestDto produtoRequestDto){
        try{
            return ResponseEntity.ok().body(produtoService.saveProduto(produtoRequestDto));
        }catch (Exception e){
            throw e;
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Produto>> getAllProdutos(){
        try{
            return ResponseEntity.ok().body(produtoService.getAllProdutos());
        }catch (Exception e){
            throw e;
        }
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<Produto>> getAllByFilterClientes(@RequestParam("situacao") String situacao,
                                                                @RequestParam("nomeProduto") String nomeProduto){
        try{
            return ResponseEntity.ok().body(produtoService.getAllByFilterprodutos(situacao,nomeProduto));
        }catch (Exception e){
            throw e;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProdutoById(@PathVariable("id") Long id) throws IOException {

        String deleteSucess = "Cliente deleted sucessfully.";
        String produtoNotFound = "Cliente Not Found";

        Optional<Produto> produtoModelOptional = produtoService.findById(id);

        if (produtoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produtoNotFound);
        }
        produtoService.deleteProdutoById(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id){

        Optional<Produto> produtoModelOptional = produtoService.findById(id);

        if (produtoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.getProdutoById(produtoModelOptional.get().getCodProduto()));
    }

    @PutMapping("/editar-produto")
    public ResponseEntity<Produto> updateProdutoById(@RequestBody ProdutoRequestDto produtoRequestDto){

        Optional<Produto> produtoModelOptional = produtoService.findById(produtoRequestDto.getCodProduto());
        if (produtoModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var produtoModel = produtoModelOptional.get();
        BeanUtils.copyProperties(produtoRequestDto,produtoModel);
        produtoModel.setCodProduto(produtoRequestDto.getCodProduto());
        produtoModel.setNomeProduto(produtoRequestDto.getNomeProduto());
        produtoModel.setCustoProduto(produtoRequestDto.getCustoProduto());
        produtoModel.setValorProduto(produtoRequestDto.getValorProduto());
        produtoModel.setSituacao(produtoRequestDto.getSituacao());
        produtoModel.setQuantidade(produtoRequestDto.getQuantidade());
        produtoModel.setIdImagem(produtoRequestDto.getIdImagem());
        produtoModel.setNomeImagem(produtoRequestDto.getNomeImagem());
        produtoModel.setUrlImagem(produtoRequestDto.getUrlImagem());

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateProdutoById(produtoModel));
    }
}
