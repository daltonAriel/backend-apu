package com.apu_api.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apu_api.entity.SUBT_APU_USUARIO;
import com.apu_api.service.UsuarioService;

@Service
public class JwtUserDetails implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	
	@Override
	public JwtUserPrincipal loadUserByUsername(String correo) throws UsernameNotFoundException {
		SUBT_APU_USUARIO usuario = usuarioService.findBySau_correo(correo);
			
		if (usuario != null) {
			
			//agregar roles
			List<GrantedAuthority> roles = usuario.getSubt_apu_usuario_rol().stream()
					.map(usuario_rol -> new SimpleGrantedAuthority(usuario_rol.getSubt_apu_rol().getSar_descripcion().name())).collect(Collectors.toList());

			
		    boolean estadoCuenta = usuario.isSau_estado_cuenta();
		    boolean accountNonExpired = true;
		    boolean credentialsNonExpired = true;
		    boolean accountNonLocked = true;
		    
			return new JwtUserPrincipal(usuario.getSau_correo(), usuario.getSau_clave(), estadoCuenta, accountNonExpired, credentialsNonExpired, 
					accountNonLocked, roles, usuario.getSau_codigo(), usuario.getSau_nombres(), usuario.getSau_apellidos());
			
		}else {
			//agregar usuario master
			throw new UsernameNotFoundException("No se encontro el usuario: " + correo);
		}		

	}

}
