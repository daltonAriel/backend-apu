package com.apu_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.apu_api.entity.SUBT_APU_UNIDAD;
import com.apu_api.exception.NotFoundExcep;
import com.apu_api.repository.UnidadRepository;
import com.apu_api.repository.Specification.StringUtil;
import com.apu_api.repository.Specification.UnidadSpecification;
import com.apu_api.security.JwtResponse;
import com.apu_api.security.JwtUserPrincipal;
import com.apu_api.security.JwtUtil;

@Service
public class UnidadService {
	
	
	@Autowired
	private UnidadRepository unidadRepo;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	
	public JwtResponse obtenerUnidades(String _cadena, Boolean estado, int _page, int _size) {
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		Page<SUBT_APU_UNIDAD> pageUnidades = unidadRepo.findAll(UnidadSpecification.byAbreviaturaLikeIgnoreAccentAndCapitalize(_cadena)
				.or(UnidadSpecification.byDescripcionLikeIgnoreAccentAndCapitalize(_cadena))
				.and(UnidadSpecification.byEstado(estado)),
				PageRequest.of(_page, _size));
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), pageUnidades);
		
		return response;
	}
	
	
	
	public JwtResponse guardarUnidad(SUBT_APU_UNIDAD _unidad) {
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		_unidad.setSaun_abreviatura(StringUtil.removeExtraSpaces(_unidad.getSaun_abreviatura()));
		_unidad.setSaun_descripcion(StringUtil.removeExtraSpaces(_unidad.getSaun_descripcion()));
		SUBT_APU_UNIDAD nuevaUnidad = unidadRepo.save(_unidad);
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), nuevaUnidad);
		
		return response;
	}
	
	
	
	public JwtResponse actualizarUnidad(SUBT_APU_UNIDAD _unidadNueva, long _id) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		if( _unidadNueva.getSaun_codigo() != _id) {
			throw new NotFoundExcep("Error al actualizar la unidad!");
		}
		
		Optional<SUBT_APU_UNIDAD> optionalUnidad = unidadRepo.findById(_id);
		if(optionalUnidad.isEmpty()) {
			throw new NotFoundExcep("Error no se encrontro la unidad!");
		}
				
		_unidadNueva.setSaun_abreviatura(StringUtil.removeExtraSpaces(_unidadNueva.getSaun_abreviatura()));
		_unidadNueva.setSaun_descripcion(StringUtil.removeExtraSpaces(_unidadNueva.getSaun_descripcion()));
		
		SUBT_APU_UNIDAD nuevaUnidad = unidadRepo.save(_unidadNueva);
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), nuevaUnidad);
		return response;
		
	}
	
	
	
	public JwtResponse eliminarUnidad(long _id) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		Optional<SUBT_APU_UNIDAD> optionalUnidad = unidadRepo.findById(_id);
		if(optionalUnidad.isEmpty()) {
			throw new NotFoundExcep("Error no se encrontro la unidad!");
		}
		
		unidadRepo.deleteById(_id);
		
		JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), null);
		return response;
	}
	
	
	
	public JwtResponse validadorAbreviatura(String _abreviatura) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		List<SUBT_APU_UNIDAD> listaUnidades = unidadRepo.findAll(UnidadSpecification.byAbreviaturaIgnoreAccentAndCapitalize(_abreviatura));
		
		if(listaUnidades.size() > 0) {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), true);
			return response;
		}else {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), false);
			return response;
		}
		
	}
	
	
	
	public JwtResponse validadorAbreviaturaId(long _id ,String _abreviatura) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		List<SUBT_APU_UNIDAD> listaUnidades = unidadRepo.findAll(UnidadSpecification.byAbreviaturaIgnoreAccentAndCapitalize(_abreviatura)
				.and(UnidadSpecification.byNotEqualUnidadCodigo(_id)));
		
		if(listaUnidades.size() > 0) {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), true);
			return response;
		}else {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), false);
			return response;
		}
		
	}
	
	
	
	public JwtResponse validadorDescripcion(String _descripcion) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		List<SUBT_APU_UNIDAD> listaUnidades = unidadRepo.findAll(UnidadSpecification.byDescripcionIgnoreAccentAndCapitalize(_descripcion));
		
		if(listaUnidades.size() > 0) {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), true);
			return response;
		}else {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), false);
			return response;
		}
		
	}
	
	
	
	public JwtResponse validadorDescripcionId(long _id ,String _descripcion) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		JwtUserPrincipal user = (JwtUserPrincipal) securityContext.getAuthentication().getPrincipal();
		
		List<SUBT_APU_UNIDAD> listaUnidades = unidadRepo.findAll(UnidadSpecification.byDescripcionIgnoreAccentAndCapitalize(_descripcion)
				.and(UnidadSpecification.byNotEqualUnidadCodigo(_id)));
		
		if(listaUnidades.size() > 0) {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), true);
			return response;
		}else {
			JwtResponse response = new JwtResponse(jwtTokenUtil.generateToken(user), false);
			return response;
		}
		
	}
	
}
