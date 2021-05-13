package br.com.adamis.pessoa.filter;

import java.util.Date;

import lombok.Data;

@Data
public class PessoasFilter {

  private Integer id;
  private String nome;
  private String sexo;
  private Date datanasc;  
  private String telefone;
}
