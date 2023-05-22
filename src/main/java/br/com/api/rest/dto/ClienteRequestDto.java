package br.com.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClienteRequestDto {

    private Long codCliente;

    private String nome;

    private String cpfOrCnpj;

    private String rgOrIe;

    private String tipoPublico;

    private String situacao;

    private List<String> telefones;
}
