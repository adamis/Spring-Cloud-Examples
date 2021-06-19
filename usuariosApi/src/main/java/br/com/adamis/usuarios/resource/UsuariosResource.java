package br.com.adamis.usuarios.resource;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import br.com.adamis.usuarios.entity.Usuarios;
import br.com.adamis.usuarios.filter.UsuariosFilter;
import br.com.adamis.usuarios.outputs.UsuariosDTO;
import br.com.adamis.usuarios.repository.UsuariosRepository;
import br.com.adamis.usuarios.service.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosResource {

	private static final Logger LOG = LoggerFactory.getLogger(UsuariosResource.class);

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private UsuariosService usuariosService;

	@PostMapping
	public ResponseEntity<Usuarios> criar(@RequestBody Usuarios usuarios, HttpServletResponse response) {
		Usuarios usuariosSalva = usuariosRepository.save(usuarios);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariosSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<UsuariosDTO> buscarPeloCodigo(@PathVariable Long codigo) {
		LOG.info("Buncando o Usuario: "+codigo);
		
		UsuariosDTO usuarios = usuariosService.findById(codigo);
		
		LOG.info("Usuario Encontrado: "+usuarios.getUsuario());
		
		return usuarios != null ? ResponseEntity.ok(usuarios) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		usuariosRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Usuarios> atualizar(@PathVariable Long codigo, @Validated @RequestBody Usuarios usuarios) {
		Usuarios usuariosSalva = usuariosService.atualizar(codigo, usuarios);
		return ResponseEntity.ok(usuariosSalva);
	}

	@GetMapping
	public Page<Usuarios> pesquisar(UsuariosFilter usuariosFilter, Pageable pageable) {
		return usuariosRepository.filtrar(usuariosFilter, pageable);
	}
}
