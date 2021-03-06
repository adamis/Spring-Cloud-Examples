package br.com.adamis.auth.entity;
// Generated 6 de mai. de 2021 09:29:04 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Usuarios generated by hbm2java
 */
@Entity
@Table(name = "usuarios")
@Data
public class Usuarios implements java.io.Serializable {

	private static final long serialVersionUID = 8300436021894055473L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "usuario", nullable = false)
	private String usuario;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "token")
	private String token;	

	@Column(name = "rules")
	private String rules;
	
}
