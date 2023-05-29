package br.com.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Produto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codProduto;

    private String nomeProduto;

    private BigDecimal custoProduto;

    private BigDecimal valorProduto;

    private Integer quantidade;

    private String situacao;

    private String dataCadastro;

    private String dataEditada;

    private String nomeImagem;

    private String idImagem;

    private String urlImagem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

}
