package com.apu_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apu_api.entity.SUBT_APU_IVA;
import com.apu_api.entity.SUBT_APU_PROYECTO;
import com.apu_api.entity.SUBT_APU_PROYECTO_IVA;
import com.apu_api.repository.IvaRepostory;
import com.apu_api.repository.ProyectoIvaRepository;
import com.apu_api.repository.ProyectoRepository;
import com.apu_api.repository.Specification.ProyectoSpecification;
import com.apu_api.security.JwtResponse;
import com.apu_api.security.JwtUserPrincipal;
import com.apu_api.security.JwtUtil;

@Service
public class ProyectoService {
	
	
	@Autowired
	private ProyectoRepository proyectoRepo;
	
	@Autowired
	private IvaRepostory ivaRepo;
	
	@Autowired
	private ProyectoIvaRepository proyectoIvaRepo;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
		
	
	public JwtResponse obtenerProyectosPorIdUsuario(String _cadena, String _fechaInicio, String _fechaFin, int _page, int _size) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		Page<SUBT_APU_PROYECTO> pageProyectos = proyectoRepo.findAll(ProyectoSpecification.byNombreLikeIgnoreAccentAndCapitalize(_cadena).or(ProyectoSpecification.byDescripcionLikeIgnoreAccentAndCapitalize(_cadena))
				.and(ProyectoSpecification.byId(user.getId()))
				.and(ProyectoSpecification.byCreationDates(_fechaInicio, _fechaFin))
				, PageRequest.of(_page, _size));
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), pageProyectos);
		return response;
	}
	
	
	public JwtResponse gaurdarProyecto(SUBT_APU_PROYECTO _proyecto) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		_proyecto.setSap_usuario_codigo(user.getId());
		SUBT_APU_PROYECTO nuevoProyecto = proyectoRepo.save(_proyecto);
		
		Optional<SUBT_APU_IVA> optionalIva = ivaRepo.findLast();
		
		if(optionalIva.isPresent()) {
			SUBT_APU_PROYECTO_IVA proyectoIva = new SUBT_APU_PROYECTO_IVA();
			proyectoIva.setSubt_apu_proyecto(nuevoProyecto);
			proyectoIva.setSubt_apu_iva(optionalIva.get());
			
			proyectoIvaRepo.save(proyectoIva);
		}
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), nuevoProyecto);
		return response;
	}
	
	
	
}
