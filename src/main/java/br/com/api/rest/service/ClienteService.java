package br.com.api.rest.service;

import br.com.api.rest.dto.ClienteRequestDto;
import br.com.api.rest.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente saveCliente(ClienteRequestDto clienteRequestDto);

    List<Cliente> getAllClientes();
}
