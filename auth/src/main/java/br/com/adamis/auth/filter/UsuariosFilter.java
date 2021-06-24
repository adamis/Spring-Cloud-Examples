package br.com.adamis.auth.filter;

import lombok.Data;

@Data
public class UsuariosFilter {
  
  private Integer id; 
  private String senha;
  private String token;
  private String usuario;

}
