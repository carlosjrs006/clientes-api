package br.com.api.rest.service;

import br.com.api.rest.dto.ClienteRequestDto;
import br.com.api.rest.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente saveCliente(ClienteRequestDto clienteRequestDto);

    List<Cliente> getAllClientes();

    List<Cliente> getAllByFilterClientes(String situacao,String nome);

    void deleteClienteById(Cliente cliente);

    Optional<Cliente> findById(Long id);

    Cliente getClienteById(Long codCliente);

    Cliente updateClienteById(Cliente cliente);

}
