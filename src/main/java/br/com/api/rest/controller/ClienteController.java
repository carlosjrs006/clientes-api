package br.com.api.rest.controller;

import br.com.api.rest.dto.ClienteRequestDto;
import br.com.api.rest.model.Cliente;
import br.com.api.rest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("")
    public ResponseEntity<Cliente> saveCliente(@RequestBody ClienteRequestDto clienteRequestDto){
         try{
             return ResponseEntity.ok().body(clienteService.saveCliente(clienteRequestDto));
         }catch (Exception e){
             throw e;
         }

    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        try{
            return ResponseEntity.ok().body(clienteService.getAllClientes());
        }catch (Exception e){
            throw e;
        }

    }


}
