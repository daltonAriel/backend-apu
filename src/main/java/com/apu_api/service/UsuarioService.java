package com.apu_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apu_api.entity.SUBT_APU_USUARIO;
import com.apu_api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	
	public SUBT_APU_USUARIO findBySau_correo(String correo) {
		return usuarioRepo.findBySau_correo(correo);
	}
	
	
}
