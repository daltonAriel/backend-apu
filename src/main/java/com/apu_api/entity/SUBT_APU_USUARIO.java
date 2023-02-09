package com.apu_api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "SUBT_APU_USUARIO")
@Data
public class SUBT_APU_USUARIO {
	
	@Column(name = "SAU_CODIGO")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sau_codigo;
	
	
	@Column(name = "SAU_CORREO", nullable = false, unique = true)
	@NotBlank
	private String sau_correo;
	
	
	@Column(name = "SAU_CLAVE", nullable = false)
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonIgnore
	private String sau_clave;
	
	
	@Column(name = "SAU_NOMBRES", nullable = false)
	@NotBlank
	private String sau_nombres;
	
	
	@Column(name = "SAU_APELLIDOS", nullable = false)
	@NotBlank
	private String sau_apellidos;
	
	
	@Column(name = "SAU_ESTADO_CUENTA", nullable = false)
	@NotBlank
	private boolean sau_estado_cuenta;
	
	
	@OneToMany(mappedBy = "subt_apu_usuario" ,cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SUBT_APU_PROYECTO> subt_apu_proyecto = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "subt_apu_usuario" ,cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<SUBT_APU_USUARIO_ROL> subt_apu_usuario_rol = new ArrayList<>();
	
	
}
