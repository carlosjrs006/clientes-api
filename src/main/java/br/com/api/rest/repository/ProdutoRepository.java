package br.com.api.rest.repository;

import br.com.api.rest.model.Cliente;
import br.com.api.rest.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select u from Produto u")
    List<Produto> getAllProdutos();

    @Query("select u from Produto u where u.situacao =?1 and u.nomeProduto LIKE %?2%")
    List<Produto> getAllByFilterProdutos(String situacao,String nomeProduto);

    @Query("select u from Produto u where u.situacao =?1")
    List<Produto> getAllByFilterSituacaoProdutos(String situacao);

    @Query("select u from Produto u where u.nomeProduto LIKE %?1%")
    List<Produto> getAllByFilterNomeProdutos(String nomeProduto);
}
