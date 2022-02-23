package br.com.os.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.os.api.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
