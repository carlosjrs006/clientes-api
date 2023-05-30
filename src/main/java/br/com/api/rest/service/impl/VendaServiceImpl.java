package br.com.api.rest.service.impl;

import br.com.api.rest.dto.ItemVendaResquetDto;
import br.com.api.rest.dto.VendaResquestDto;
import br.com.api.rest.model.ItemVenda;
import br.com.api.rest.model.Produto;
import br.com.api.rest.model.Venda;
import br.com.api.rest.repository.ItemVendaRepository;
import br.com.api.rest.repository.ProdutoRepository;
import br.com.api.rest.repository.VendaRepository;
import br.com.api.rest.service.ProdutoService;
import br.com.api.rest.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaServiceImpl implements VendaService {


    private final VendaRepository vendaRepository;


    private final ProdutoRepository produtoRepository;


    private final ProdutoService produtoService;

    private final ItemVendaRepository itemVendaRepository;


    @Override
    public List<Produto> getAllByFilterprodutos(String nomeProduto) {
        return produtoRepository.getAllByFilterNomeAndAtivoProdutos(nomeProduto,"ativo");
    }

    @Override
    @Transactional
    public Venda saveVenda(VendaResquestDto vendaResquestDto) {
        List<ItemVenda> itemVendas = new ArrayList<>();
        Venda venda = new Venda();
        // Realize quaisquer validações de negócio necessárias antes de salvar a venda
        if (vendaResquestDto.getItens() == null || vendaResquestDto.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda deve ter pelo menos um item.");
        }

        BeanUtils.copyProperties(vendaResquestDto, venda);
        venda.setDataCadastroVenda(formattedDate());
        Venda saveVenda = vendaRepository.save(venda);

        for(ItemVenda item : venda.getItens()){
            ItemVenda itemVenda = new ItemVenda();

            itemVenda.setQuantidade(item.getQuantidade());
            itemVenda.setProduto(item.getProduto());
            itemVenda.setValorTotalItem(item.getValorTotalItem());
            itemVenda.setVenda(saveVenda);

            itemVendaRepository.save(itemVenda);
            itemVendas.add(itemVenda);
        }

        saveVenda.setItens(itemVendas);
        atualizarEstoqueProdutos(saveVenda);
        return vendaRepository.save(saveVenda);

    }

    private void atualizarEstoqueProdutos(Venda venda) {
        List<ItemVenda> itensVenda = venda.getItens();
        for (ItemVenda itemVenda : itensVenda) {
            Produto produto = itemVenda.getProduto();
            int quantidadeVendida = itemVenda.getQuantidade();
            int estoqueAtualizado = produto.getQuantidade() - quantidadeVendida;
            produto.setQuantidade(estoqueAtualizado);
            produtoService.updateProdutoById(produto);
        }
    }

    private String formattedDate(){
        Calendar backendDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(backendDate.getTime());
    }
}
