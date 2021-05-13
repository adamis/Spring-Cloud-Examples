package br.com.adamis.usuarios.repository;

import br.com.adamis.usuarios.entity.Usuarios;
import br.com.adamis.usuarios.repository.usuarios.UsuariosRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository
    extends JpaRepository<Usuarios, Long>, UsuariosRepositoryQuery {}
