package br.com.os.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.os.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
