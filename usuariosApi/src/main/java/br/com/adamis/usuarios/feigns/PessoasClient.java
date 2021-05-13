package br.com.adamis.usuarios.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.adamis.usuarios.outputs.PessoasDTO;

@FeignClient("pessoa")
public interface PessoasClient {

	@RequestMapping("/pessoas/{id}")
	ResponseEntity<PessoasDTO> findPessoaId(@PathVariable long id);
}
