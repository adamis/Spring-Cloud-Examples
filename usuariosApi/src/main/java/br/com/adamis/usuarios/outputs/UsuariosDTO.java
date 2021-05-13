package br.com.adamis.usuarios.outputs;

import lombok.Data;

@Data
public class UsuariosDTO {
	private Long id;	
	private PessoasDTO pessoas;
	private String usuario;
	private String senha;
	private String chave;
}
