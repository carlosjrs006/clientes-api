package br.com.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Venda implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codVenda;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<Produto> produtos;

    private String dataCadastroVenda;

    private String formaPagamento;

    private BigDecimal valorTotalVenda;
}
