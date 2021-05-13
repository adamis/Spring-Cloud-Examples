package br.com.adamis.usuarios.repository.usuarios;

import br.com.adamis.usuarios.entity.Usuarios;
import br.com.adamis.usuarios.filter.UsuariosFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuariosRepositoryQuery {

  public Page<Usuarios> filtrar(UsuariosFilter usuariosFilter, Pageable pageable);
}
