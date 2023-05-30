package br.com.api.rest.dto;

import br.com.api.rest.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ItemVendaResquetDto {

    private Produto produto;

    private Integer quantidade;

    private BigDecimal valorTotalItem;
}
