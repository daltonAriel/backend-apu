package com.apu_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apu_api.entity.SUBT_APU_PROYECTO;
import com.apu_api.security.JwtResponse;
import com.apu_api.service.ProyectoService;


@RestController 
@RequestMapping("/apu/proyecto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProyectoController {
	
	@Autowired
	private ProyectoService proyectoService;
	
		
	
	@GetMapping
	public ResponseEntity<?> getData(@RequestParam(value="fechaInicio", required = false)  String _fechaInicio,@RequestParam(value="fechaFin", required = false) String _fechaFin,
			@RequestParam(value="cadenaBusqueda", required = false) String _cadenaBusqueda, @RequestParam (value = "page", defaultValue = "0", required = false) int _page, 
			@RequestParam(value = "size", defaultValue = "20", required = false) int _size){
		
		JwtResponse resp = proyectoService.obtenerProyectosPorIdUsuario(_cadenaBusqueda, _fechaInicio, _fechaFin, _page, _size);
		
		return ResponseEntity.ok(resp);
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> guardarProyecto(@RequestBody(required = false) SUBT_APU_PROYECTO _proyecto ) {
		JwtResponse resp = proyectoService.gaurdarProyecto(_proyecto);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarProyecto(@RequestBody(required = false) SUBT_APU_PROYECTO _proyecto, @PathVariable(value = "id") long _id) {
		JwtResponse resp = proyectoService.actualizarProyecto(_proyecto, _id);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarProyecto(@PathVariable(value = "id") long _id) {
		JwtResponse resp = proyectoService.eliminarProyecto(_id);
		return ResponseEntity.ok(resp);
	}
	
}
