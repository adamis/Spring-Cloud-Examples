package br.com.adamis.pessoa.repository.pessoas;

import br.com.adamis.pessoa.entity.Pessoas;
import br.com.adamis.pessoa.filter.PessoasFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoasRepositoryQuery {

  public Page<Pessoas> filtrar(PessoasFilter pessoasFilter, Pageable pageable);
}
