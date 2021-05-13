package br.com.adamis.pessoa.resource;

import br.com.adamis.pessoa.entity.Pessoas;
import br.com.adamis.pessoa.filter.PessoasFilter;
import br.com.adamis.pessoa.repository.PessoasRepository;
import br.com.adamis.pessoa.service.PessoasService;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoasResource {

  @Autowired private PessoasRepository pessoasRepository;

  @Autowired private PessoasService pessoasService;

  @PostMapping
  public ResponseEntity<Pessoas> criar(@RequestBody Pessoas pessoas, HttpServletResponse response) {
    Pessoas pessoasSalva = pessoasRepository.save(pessoas);
    return ResponseEntity.status(HttpStatus.CREATED).body(pessoasSalva);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Pessoas> buscarPeloCodigo(@PathVariable Long codigo) {
    Optional<Pessoas> pessoas = pessoasRepository.findById(codigo);
    return pessoas != null ? ResponseEntity.ok(pessoas.get()) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long codigo) {
    pessoasRepository.deleteById(codigo);
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<Pessoas> atualizar(
      @PathVariable Long codigo, @Validated @RequestBody Pessoas pessoas) {
    Pessoas pessoasSalva = pessoasService.atualizar(codigo, pessoas);
    return ResponseEntity.ok(pessoasSalva);
  }

  @GetMapping
  public Page<Pessoas> pesquisar(PessoasFilter pessoasFilter, Pageable pageable) {
    return pessoasRepository.filtrar(pessoasFilter, pageable);
  }
}
