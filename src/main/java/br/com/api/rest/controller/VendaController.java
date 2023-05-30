package br.com.api.rest.controller;

import br.com.api.rest.dto.ProdutoRequestDto;
import br.com.api.rest.dto.VendaResquestDto;
import br.com.api.rest.model.Produto;
import br.com.api.rest.model.Venda;
import br.com.api.rest.service.VendaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("")
    public ResponseEntity<Venda> saveVenda(@RequestBody VendaResquestDto vendaResquestDto){
        try{
            return ResponseEntity.ok().body(vendaService.saveVenda(vendaResquestDto));
        }catch (Exception e){
            throw e;
        }

    }


    @GetMapping("/filtros")
    public ResponseEntity<List<Produto>> getAllByFilterClientes(@RequestParam("nomeProduto") String nomeProduto){
        try{
            return ResponseEntity.ok().body(vendaService.getAllByFilterprodutos(nomeProduto));
        }catch (Exception e){
            throw e;
        }

    }

}
