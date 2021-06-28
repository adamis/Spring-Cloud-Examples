package br.com.adamis.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adamis.auth.entity.Usuarios;
import br.com.adamis.auth.repository.usuarios.UsuariosRepositoryQuery;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>, UsuariosRepositoryQuery {

	Optional<Usuarios> findByUsuarioAndSenha(String name, String senha);
		
}
