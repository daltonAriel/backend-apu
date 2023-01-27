package com.apu_api.entity.embeddedId;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class UsuarioRolCodigo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private long saur_usuario_codigo;
	
	
	private long saur_rol_codigo;

}
