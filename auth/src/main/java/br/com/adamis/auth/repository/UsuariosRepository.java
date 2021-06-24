package br.com.adamis.auth.repository;

import br.com.adamis.auth.entity.Usuarios;
import br.com.adamis.auth.repository.usuarios.UsuariosRepositoryQuery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>, UsuariosRepositoryQuery {

	Optional<Usuarios> findByUsuarioAndSenha(String name, String senha);
}
