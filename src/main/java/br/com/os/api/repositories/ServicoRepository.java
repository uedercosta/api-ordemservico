package br.com.os.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.os.api.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{

}
