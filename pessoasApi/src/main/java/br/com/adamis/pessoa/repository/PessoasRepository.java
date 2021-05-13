package br.com.adamis.pessoa.repository;

import br.com.adamis.pessoa.entity.Pessoas;
import br.com.adamis.pessoa.repository.pessoas.PessoasRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoasRepository extends JpaRepository<Pessoas, Long>, PessoasRepositoryQuery {}
