package br.com.api.rest.dto;

import br.com.api.rest.model.ItemVenda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VendaResquestDto {

    private List<ItemVenda> itens;

    private String formaPagamento;

    private BigDecimal valorTotalVenda;
}
