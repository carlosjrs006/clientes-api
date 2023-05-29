package br.com.api.rest.controller;

import br.com.api.rest.model.Produto;
import br.com.api.rest.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;


    @GetMapping("/filtros")
    public ResponseEntity<List<Produto>> getAllByFilterClientes(@RequestParam("nomeProduto") String nomeProduto){
        try{
            return ResponseEntity.ok().body(vendaService.getAllByFilterprodutos(nomeProduto));
        }catch (Exception e){
            throw e;
        }

    }

}
