package br.com.adamis.usuarios.filter;

import java.util.Date;

import lombok.Data;

@Data
public class UsuariosFilter {

	private Integer id;
	private String usuario;
	private String email;
	private String senha;
	private String foto;
	private String activationToken;
	private Date activationTokenExpiration;
	private String passwordToken;
	private Date passwordTokenExpiration;
	private String accessToken;
	private Date accessTokenExpiration;
	private Date cadastrado;
	private Date modificado;
	private Integer perfil;
	private Integer status;
	private Integer tema;

}
