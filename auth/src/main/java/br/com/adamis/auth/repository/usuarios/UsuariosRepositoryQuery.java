package br.com.adamis.auth.repository.usuarios;

import br.com.adamis.auth.entity.Usuarios;
import br.com.adamis.auth.filter.UsuariosFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuariosRepositoryQuery {

  public Page<Usuarios> filtrar(UsuariosFilter usuariosFilter, Pageable pageable);
}
