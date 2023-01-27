package com.apu_api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

public class JwtUserPrincipal extends User{

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private long id;
	
	
	@Getter
	@Setter
	private String nombres;
	
	
	@Getter
	@Setter
	private String apellidos;

	
	public JwtUserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, long _id, String _nombres, String _apellidos) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.id = _id;
		
		this.nombres = _nombres;
		
		this.apellidos = _apellidos;
	}

}
