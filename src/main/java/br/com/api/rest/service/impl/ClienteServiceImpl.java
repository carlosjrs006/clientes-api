package br.com.api.rest.service.impl;

import br.com.api.rest.dto.ClienteRequestDto;
import br.com.api.rest.model.Cliente;
import br.com.api.rest.repository.ClienteRepository;
import br.com.api.rest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
					.dataCadastro(formattedDate())
					.build();
			return clienteRepository.save(cliente);
		}else{
			throw new RuntimeException("Cpf/Cnpj ja existe.");
		}

	}

	private String formattedDate(){
		Calendar backendDate = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(backendDate.getTime());
	}

	@Override
	public Cliente updateClienteById(Cliente clienteRequestDto) {
		Cliente cliente =  Cliente.builder()
				.codCliente(clienteRequestDto.getCodCliente())
				.cpfOrCnpj(clienteRequestDto.getCpfOrCnpj())
				.rgOrIe(clienteRequestDto.getRgOrIe())
				.nome(clienteRequestDto.getNome())
				.situacao(clienteRequestDto.getSituacao())
				.tipoPublico(clienteRequestDto.getTipoPublico())
				.telefones(clienteRequestDto.getTelefones())
				.dataCadastro(formattedDate())
				.build();
		return clienteRepository.save(cliente);

	}

	@Override
	public Cliente getClienteById(Long codCliente) {
		return clienteRepository.getById(codCliente);
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteClienteById(Cliente cliente) {
		if(!cliente.getTelefones().isEmpty()) clienteRepository.deleteAllTelefonesIntoCliente(cliente.getCodCliente());
		clienteRepository.deleteById(cliente.getCodCliente());
	}


	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.getAllClientes();
	}


	@Override
	public List<Cliente> getAllByFilterClientes(String situacao,String nome) {
		if (!StringUtils.isEmpty(situacao) && !StringUtils.isEmpty(nome)){
			return clienteRepository.getAllByFilterClientes(situacao,nome);
		} else if(StringUtils.isEmpty(situacao) && !StringUtils.isEmpty(nome)){
			return clienteRepository.getAllByFilterNomeClientes(nome);
		}else if(!StringUtils.isEmpty(situacao) && StringUtils.isEmpty(nome)){
			return clienteRepository.getAllByFilterSituacaoClientes(situacao);
		}
		return List.of();
	}

	public Boolean verifyExistsCpfOrCnpj(String cpfOrCnpj) {
		Cliente clienteByCpfOrCnpj = clienteRepository.findClienteByCpf(cpfOrCnpj);
		if(Objects.nonNull(clienteByCpfOrCnpj)){
			return true;
		}
		return false;
	}
}
