package com.apu_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class SUBT_APU_UNIDAD {
	
	
	@Column(name = "SAUN_CODIGO")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long saun_codigo;
	
	
	@Column(name = "SAUN_ABREVIATURA", nullable = false, unique = true)
	@NotBlank
	private String saun_abreviatura;
	
	
	@Column(name = "SAUN_DESCRIPCION", nullable = false, unique = true)
	@NotBlank
	private String saun_descripcion;
	
	
	@Column(name = "SAUN_ESTADO", nullable = false)
	private boolean saun_estado;
	
	
	
}
