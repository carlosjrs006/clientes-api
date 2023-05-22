package br.com.api.rest.repository;

import br.com.api.rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
//	@Query("select u from Usuario u where u.login = ?1")
//


    	@Query("select u from Cliente u where u.cpfOrCnpj = ?1")
        Cliente findClienteByCpf(String cpfOrCnpj);

        @Query("select u from Cliente u")
        List<Cliente> getAllClientes();
	
	//1 e 2 para referenciar os paramentros
//	@Transactional
//	@Modifying
//	@Query(nativeQuery = true, value = "update usuario set token = ?1 where login = ?2")
//	Cliente atualizaTokenUser(String token, String login);

}
