package br.com.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

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

    private String custoProduto;

    private String valorProduto;

    private Integer quantidade;

    private String situacao;

    private String dataCadastro;

    private String dataEditada;

    private String nomeImagem;

    private String idImagem;

    private String urlImagem;

}
