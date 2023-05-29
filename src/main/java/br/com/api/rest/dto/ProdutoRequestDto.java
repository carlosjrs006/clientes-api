package br.com.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProdutoRequestDto {

    private Long codProduto;

    private String nomeProduto;

    private BigDecimal custoProduto;

    private String situacao;

    private BigDecimal valorProduto;

    private Integer quantidade;

    private String nomeImagem;

    private String idImagem;

    private String urlImagem;
}
