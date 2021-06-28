package br.com.adamis.auth.service;

import java.util.ArrayList;import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adamis.auth.entity.Usuarios;
import br.com.adamis.auth.repository.UsuariosRepository;

@Service
public class UsuariosService implements AuthenticationProvider {

	@Autowired private UsuariosRepository usuariosRepository;

	@Autowired private PasswordEncoder passwordEncoder;
	
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

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String name = authentication.getName();        
        String password = authentication.getCredentials().toString();
        
        Authentication auth = null;

        System.err.println("Name: "+name);
        System.err.println("Password: "+password);
        System.err.println("Password Encode: "+passwordEncoder.encode(password));
        
        Optional<Usuarios> optUsuario = usuariosRepository.findByUsuarioAndSenha(name,password);
        
        if(optUsuario.isPresent()) {
        	
        	String[] rules = optUsuario.get().getRules().split(",");
        	
        	//.authorizedGrantTypes("authorization_code", "refresh_token", "implicit")
        	
        	List<GrantedAuthority> grantedAuths = new ArrayList<>();        	      	
        	grantedAuths.add(new SimpleGrantedAuthority("refresh_token"));
        	
        	for (String rule : rules) {
        		grantedAuths.add(new SimpleGrantedAuthority(rule.toUpperCase()));	
			}
        	
        	auth = new UsernamePasswordAuthenticationToken(name,password,grantedAuths);
        	
        }      
        
		return auth;
	}

	
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);		
	}


}
