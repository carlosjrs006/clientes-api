package br.com.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Cliente implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codCliente;

	private String nome;

	private String cpfOrCnpj;

	private String rgOrIe;

	private String tipoPublico;

	private String situacao;

	private String dataCadastro;

	@ElementCollection
	@CollectionTable(name = "cliente_telefones", joinColumns = @JoinColumn(name = "cod_cliente"))
	@Column(name = "telefone")
	private List<String> telefones;

}
