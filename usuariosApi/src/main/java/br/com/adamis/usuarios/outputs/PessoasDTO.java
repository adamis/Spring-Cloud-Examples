package br.com.adamis.usuarios.outputs;

import java.util.Date;

import lombok.Data;

@Data
public class PessoasDTO {

	private Long id;	
	private String nome;	
	private String sexo;	
	private Date dataNasc;	
	private String telefone;

}
