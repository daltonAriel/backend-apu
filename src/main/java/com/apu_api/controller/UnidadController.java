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

import com.apu_api.entity.SUBT_APU_UNIDAD;
import com.apu_api.security.JwtResponse;
import com.apu_api.service.UnidadService;

@RestController
@RequestMapping("/apu/unidad")
@CrossOrigin("*")
public class UnidadController {
	
	@Autowired
	private UnidadService unidadService;
	
	
	@GetMapping
	public ResponseEntity<?> getData(@RequestParam(value="cadenaBusqueda", required = false) String _cadenaBusqueda, @RequestParam(value="estado", required = false) Boolean _estado,
			@RequestParam (value = "page", defaultValue = "0", required = false) int _page, @RequestParam(value = "size", defaultValue = "20", required = false) int _size){
		JwtResponse resp = unidadService.obtenerUnidades(_cadenaBusqueda, _estado, _page, _size);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> guardarUnidad(@RequestBody(required = false) SUBT_APU_UNIDAD _proyecto ) {
		JwtResponse resp = unidadService.guardarUnidad(_proyecto);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarUnidad(@RequestBody(required = false) SUBT_APU_UNIDAD _proyecto, @PathVariable(value = "id") long _id ) {
		JwtResponse resp = unidadService.actualizarUnidad(_proyecto, _id);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUnidad(@PathVariable(value = "id") long _id) {
		JwtResponse resp = unidadService.eliminarUnidad(_id);
		return ResponseEntity.ok(resp);
	}
	
	
	@GetMapping("/validador-abreviatura")
	public ResponseEntity<?> validadorAbreviatura(@RequestParam(value="abreviatura", required = false) String _abreviatura) {
		JwtResponse resp = unidadService.validadorAbreviatura(_abreviatura);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@GetMapping("/validador-abreviatura/{id}")
	public ResponseEntity<?> validadorAbreviaturaId(@PathVariable(value = "id") long _unidadId, @RequestParam(value="abreviatura", required = false) String _abreviatura) {
		JwtResponse resp = unidadService.validadorAbreviaturaId(_unidadId,_abreviatura);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@GetMapping("/validador-descripcion")
	public ResponseEntity<?> validadorDescripcion(@RequestParam(value="descripcion", required = false) String _descripcion) {
		JwtResponse resp = unidadService.validadorDescripcion(_descripcion);
		return ResponseEntity.ok(resp);
	}
	
	
	
	@GetMapping("/validador-descripcion/{id}")
	public ResponseEntity<?> validadorDescripcionId(@PathVariable(value = "id") long _unidadId, @RequestParam(value="descripcion", required = false) String _descripcion) {
		JwtResponse resp = unidadService.validadorDescripcionId(_unidadId,_descripcion);
		return ResponseEntity.ok(resp);
	}
	
	
}
