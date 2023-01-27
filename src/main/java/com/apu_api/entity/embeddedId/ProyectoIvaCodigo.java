package com.apu_api.entity.embeddedId;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProyectoIvaCodigo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private long sapi_proyecto_codigo;
	
	
	private long sapi_iva_codigo;
	
}
