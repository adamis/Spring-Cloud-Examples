package br.com.adamis.pessoa.service;

import br.com.adamis.pessoa.entity.Pessoas;
import br.com.adamis.pessoa.repository.PessoasRepository;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoasService {

  @Autowired private PessoasRepository pessoasRepository;

  public Pessoas atualizar(Long codigo, Pessoas pessoas) {
    Pessoas pessoasSalva = buscarPeloCodigo(codigo);

    BeanUtils.copyProperties(pessoas, pessoasSalva, "id");
    return pessoasRepository.save(pessoasSalva);
  }

  public Pessoas buscarPeloCodigo(Long codigo) {
    Optional<Pessoas> pessoasSalva = pessoasRepository.findById(codigo);
    if (pessoasSalva == null) {
      throw new EmptyResultDataAccessException(1);
    }
    return pessoasSalva.get();
  }
}
