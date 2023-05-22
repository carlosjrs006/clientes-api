package br.com.api.rest.service.impl;

import br.com.api.rest.dto.ClienteRequestDto;
import br.com.api.rest.model.Cliente;
import br.com.api.rest.repository.ClienteRepository;
import br.com.api.rest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente saveCliente(ClienteRequestDto clienteRequestDto) {
		Boolean verifyExistsCpfOrCnpj = verifyExistsCpfOrCnpj(clienteRequestDto.getCpfOrCnpj());
		if(!verifyExistsCpfOrCnpj){
			Cliente cliente =  Cliente.builder()
					.cpfOrCnpj(clienteRequestDto.getCpfOrCnpj())
					.rgOrIe(clienteRequestDto.getRgOrIe())
					.nome(clienteRequestDto.getNome())
					.situacao(clienteRequestDto.getSituacao())
					.tipoPublico(clienteRequestDto.getTipoPublico())
					.telefones(clienteRequestDto.getTelefones())
					.dataCadastro(LocalDate.now(ZoneId.of("UTC")))
					.build();
			return clienteRepository.save(cliente);
		}else{
			throw new RuntimeException("Cpf/Cnpj ja existe.");
		}

	}

	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.getAllClientes();
	}

	public Boolean verifyExistsCpfOrCnpj(String cpfOrCnpj) {
		Cliente clienteByCpfOrCnpj = clienteRepository.findClienteByCpf(cpfOrCnpj);
		if(Objects.nonNull(clienteByCpfOrCnpj)){
			return true;
		}
		return false;
	}
}
