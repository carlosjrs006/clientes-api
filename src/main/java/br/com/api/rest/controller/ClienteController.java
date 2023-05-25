package br.com.api.rest.controller;

import br.com.api.rest.dto.ClienteRequestDto;
import br.com.api.rest.model.Cliente;
import br.com.api.rest.service.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
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

    @GetMapping("/filtros")
    public ResponseEntity<List<Cliente>> getAllByFilterClientes(@RequestParam("situacao") String situacao,
                                                                @RequestParam("nome") String nome){
        try{
            return ResponseEntity.ok().body(clienteService.getAllByFilterClientes(situacao,nome));
        }catch (Exception e){
            throw e;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClienteById(@PathVariable("id") Long id){

            String deleteSucess = "Cliente deleted sucessfully.";
            String clienteNotFound = "Cliente Not Found";

            Optional<Cliente> clienteModelOptional = clienteService.findById(id);

            if (clienteModelOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clienteNotFound);
            }
            clienteService.deleteClienteById(clienteModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id){

        Optional<Cliente> clienteModelOptional = clienteService.findById(id);
        if (clienteModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getClienteById(clienteModelOptional.get().getCodCliente()));
    }

    @PutMapping("/editar-cliente")
    public ResponseEntity<Cliente> updateClienteById(@RequestBody ClienteRequestDto clienteRequestDto){

        Optional<Cliente> clienteModelOptional = clienteService.findById(clienteRequestDto.getCodCliente());
        if (clienteModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var clienteModel = clienteModelOptional.get();
        BeanUtils.copyProperties(clienteRequestDto,clienteModel);
        clienteModel.setCodCliente(clienteRequestDto.getCodCliente());
        clienteModel.setNome(clienteRequestDto.getNome());
        clienteModel.setCpfOrCnpj(clienteRequestDto.getCpfOrCnpj());
        clienteModel.setRgOrIe(clienteRequestDto.getRgOrIe());
        clienteModel.setSituacao(clienteRequestDto.getSituacao());
        clienteModel.setTipoPublico(clienteRequestDto.getTipoPublico());
        clienteModel.setTelefones(clienteRequestDto.getTelefones());

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateClienteById(clienteModel));
    }
}
