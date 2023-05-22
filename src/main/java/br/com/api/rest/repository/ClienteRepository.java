package br.com.api.rest.repository;

import br.com.api.rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    	@Query("select u from Cliente u where u.cpfOrCnpj = ?1")
        Cliente findClienteByCpf(String cpfOrCnpj);

        @Query("select u from Cliente u")
        List<Cliente> getAllClientes();

        @Query("select u from Cliente u where u.situacao =?1 and u.nome LIKE %?2%")
        List<Cliente> getAllByFilterClientes(String situacao,String nome);

        @Query("select u from Cliente u where u.situacao =?1")
        List<Cliente> getAllByFilterSituacaoClientes(String situacao);

        @Query("select u from Cliente u where u.nome LIKE %?1%")
        List<Cliente> getAllByFilterNomeClientes(String nome);


        @Modifying
        @Query(value = "delete from cliente_telefones where cod_cliente = :id", nativeQuery = true)
        void deleteAllTelefonesIntoCliente(@Param("id") Long id);


}
