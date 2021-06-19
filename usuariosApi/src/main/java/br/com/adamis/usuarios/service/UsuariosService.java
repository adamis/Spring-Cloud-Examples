package br.com.adamis.usuarios.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.adamis.usuarios.entity.Usuarios;
import br.com.adamis.usuarios.feigns.PessoasClient;
import br.com.adamis.usuarios.outputs.PessoasDTO;
import br.com.adamis.usuarios.outputs.UsuariosDTO;
import br.com.adamis.usuarios.repository.UsuariosRepository;

@Service
public class UsuariosService {

	@Autowired private UsuariosRepository usuariosRepository;
	
	@Autowired private PessoasClient pessoasClient;
	
	@Autowired private DiscoveryClient discoveryClient;
	
	//@Autowired private RestTemplate restTemplate;
	
	public Usuarios atualizar(Long codigo, Usuarios usuarios) {
		Usuarios usuariosSalva = buscarPeloCodigo(codigo);

		BeanUtils.copyProperties(usuarios, usuariosSalva, "id");
		return usuariosRepository.save(usuariosSalva);
	}

	public Usuarios buscarPeloCodigo(Long codigo) {
		Optional<Usuarios> usuariosSalva = usuariosRepository.findById(codigo);
		if (usuariosSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuariosSalva.get();
	}

	@HystrixCommand(fallbackMethod = "reSendFindByAd")
	public UsuariosDTO findById(Long codigo) {		
		
		Usuarios usuario = buscarPeloCodigo(codigo);
		
		UsuariosDTO usuariosDTO = new UsuariosDTO();
		BeanUtils.copyProperties(usuario, usuariosDTO);
		
		ResponseEntity<PessoasDTO> exchange = pessoasClient.findPessoaId(usuario.getPessoa());
		
		discoveryClient.getInstances("pessoa").stream()
		.forEach( pessoa ->{			
			System.err.println("URI: "+pessoa.getUri());			
			System.err.println("--------------------------");
		});
		
		//ResponseEntity<PessoasDTO> exchange = restTemplate.exchange("http://pessoa/pessoas/"+usuario.getPessoa(), HttpMethod.GET, null, PessoasDTO.class);	
		usuariosDTO.setPessoas(exchange.getBody());
		
		return usuariosDTO;
	}
	
	public UsuariosDTO reSendFindByAd(Long codigo) {		
		
		Usuarios usuario = buscarPeloCodigo(codigo);
		
		UsuariosDTO usuariosDTO = new UsuariosDTO();
		BeanUtils.copyProperties(usuario, usuariosDTO);
		
		return usuariosDTO;
	}
	
}
