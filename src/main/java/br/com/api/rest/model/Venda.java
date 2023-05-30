package br.com.api.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Venda implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codVenda;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemVenda> itens;

    private String dataCadastroVenda;

    private String formaPagamento;

    private BigDecimal valorTotalVenda;

    @Override
    public String toString() {
        return "Venda{" +
                "codVenda=" + codVenda +
                ", itens=" + itens +
                ", dataCadastroVenda='" + dataCadastroVenda + '\'' +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", valorTotalVenda=" + valorTotalVenda +
                '}';
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public void addItemVenda(ItemVenda itemVenda) {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        itens.add(itemVenda);
    }

    public void removeItemVenda(ItemVenda itemVenda) {
        itens.remove(itemVenda);
        itemVenda.setVenda(null); // Remove a referência à venda
    }

    public Long getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Long codVenda) {
        this.codVenda = codVenda;
    }

    public String getDataCadastroVenda() {
        return dataCadastroVenda;
    }

    public void setDataCadastroVenda(String dataCadastroVenda) {
        this.dataCadastroVenda = dataCadastroVenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorTotalVenda() {
        return valorTotalVenda;
    }

    public void setValorTotalVenda(BigDecimal valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }
}
