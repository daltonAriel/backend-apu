package com.apu_api.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.apu_api.entity.embeddedId.UsuarioRolCodigo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name = "SUBT_APU_USUARIO_ROL")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "saur_codigo")
@Data
public class SUBT_APU_USUARIO_ROL {
	
	@EmbeddedId
	private UsuarioRolCodigo saur_codigo = new UsuarioRolCodigo();
	
	
	@ManyToOne
	@MapsId("saur_usuario_codigo")
	@JoinColumn(name = "SAUR_USUARIO_CODIGO", referencedColumnName = "SAU_CODIGO")
	private SUBT_APU_USUARIO subt_apu_usuario;
	
	
	@ManyToOne
	@MapsId("saur_rol_codigo")
	@JoinColumn(name = "SAUR_ROL_CODIGO", referencedColumnName = "SAR_CODIGO")
	private SUBT_APU_ROL subt_apu_rol;
	
}
