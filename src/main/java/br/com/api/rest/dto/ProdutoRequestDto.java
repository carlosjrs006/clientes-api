package br.com.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProdutoRequestDto {

    private Long codProduto;

    private String nomeProduto;

    private String custoProduto;

    private String situacao;

    private String valorProduto;

    private Integer quantidade;

    private String nomeImagem;

    private String idImagem;

    private String urlImagem;
}
