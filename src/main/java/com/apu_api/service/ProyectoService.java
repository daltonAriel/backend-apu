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
import com.apu_api.exception.ForbiddenExcep;
import com.apu_api.exception.InternalErrorExcep;
import com.apu_api.exception.NotAllowedExcep;
import com.apu_api.exception.NotFoundExcep;
import com.apu_api.repository.IvaRepostory;
import com.apu_api.repository.ProyectoIvaRepository;
import com.apu_api.repository.ProyectoRepository;
import com.apu_api.repository.Specification.ProyectoSpecification;
import com.apu_api.repository.Specification.StringUtil;
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
	
	
	@Transactional
	public JwtResponse gaurdarProyecto(SUBT_APU_PROYECTO _proyectoNuevo) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		_proyectoNuevo.setSap_usuario_codigo(user.getId());
		
		_proyectoNuevo.setSap_nombre(StringUtil.removeExtraSpaces(_proyectoNuevo.getSap_nombre()));
		_proyectoNuevo.setSap_descripcion(StringUtil.removeExtraSpaces(_proyectoNuevo.getSap_descripcion()));
		
		SUBT_APU_PROYECTO nuevoProyecto = proyectoRepo.save(_proyectoNuevo);
		
		Optional<SUBT_APU_IVA> optionalIva = ivaRepo.findLast();
		
		if(optionalIva.isPresent()) {
			SUBT_APU_IVA iva = optionalIva.get();
			SUBT_APU_PROYECTO_IVA proyectoIva = new SUBT_APU_PROYECTO_IVA();
			
			proyectoIva.setSubt_apu_iva(iva);
			proyectoIva.setSubt_apu_proyecto(nuevoProyecto);
			proyectoIva.setSapi_porcentaje(iva.getSai_porcentaje());
			
			proyectoIvaRepo.save(proyectoIva);
		}else {
			throw new InternalErrorExcep("No hay un IVA establecido para crear el proyecto!");
		}
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), nuevoProyecto);
		return response;
	}
	
	
	public JwtResponse actualizarProyecto(SUBT_APU_PROYECTO _proyectoNuevo, long _id) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		if( _proyectoNuevo.getSap_codigo() != _id) {
			throw new NotFoundExcep("Error al actualizar el proyecto!");
		}
		
		Optional<SUBT_APU_PROYECTO> optionalProyecto = proyectoRepo.findById(_id);
		if(optionalProyecto.isEmpty()) {
			throw new NotFoundExcep("Error no se encrontro el proyecto!");
		}
		
			
		SUBT_APU_PROYECTO proyectoAntiguo = optionalProyecto.get();

		if(proyectoAntiguo.getSap_usuario_codigo() != user.getId()) {
			throw new ForbiddenExcep("Error, no tiene acceso a este proyecto!");
		}
		
		if(proyectoAntiguo.isSap_estado_finalizacion()) {
			throw new NotAllowedExcep("Error el proyecto esta finalizado y no se puede modificar, consulte con el administrador!");
		}
		
		_proyectoNuevo.setSap_usuario_codigo(user.getId());
		_proyectoNuevo.setSap_nombre(StringUtil.removeExtraSpaces(_proyectoNuevo.getSap_nombre()));
		_proyectoNuevo.setSap_descripcion(StringUtil.removeExtraSpaces(_proyectoNuevo.getSap_descripcion()));
		
		SUBT_APU_PROYECTO nuevoProyecto = proyectoRepo.save(_proyectoNuevo);
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), nuevoProyecto);
		return response;
		
	}
	
	
	public JwtResponse eliminarProyecto(long _id) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		Optional<SUBT_APU_PROYECTO> optionalProyecto = proyectoRepo.findById(_id);
		
		if(optionalProyecto.isEmpty()) {
			throw new NotFoundExcep("Error no se encrontro el proyecto!");
		}
		
		SUBT_APU_PROYECTO proyecto = optionalProyecto.get();
		
		if(proyecto.getSap_usuario_codigo() != user.getId()) {
			throw new ForbiddenExcep("Error, no tiene acceso a este proyecto!");
		}
		proyectoRepo.deleteById(_id);

		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), null);
		return response;
	}
	
	
	
}
