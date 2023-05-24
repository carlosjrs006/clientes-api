package br.com.api.rest.controller;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.model.Produto;
import br.com.api.rest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
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
}
