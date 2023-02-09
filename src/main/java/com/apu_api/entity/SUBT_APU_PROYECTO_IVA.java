package com.apu_api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.apu_api.entity.embeddedId.ProyectoIvaCodigo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "sapi_codigo")
@Data
public class SUBT_APU_PROYECTO_IVA {
	
	
	@EmbeddedId
	private ProyectoIvaCodigo sapi_codigo = new ProyectoIvaCodigo();
	
	
	@Column(name = "SAPI_PORCENTAJE", nullable = false)
	private BigDecimal sapi_porcentaje;
	
	
	@Column(name = "SAPI_FECHA_ACTUALIZACION", nullable = false, insertable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date sapi_fecha_actualizacion;
	
	
	
	@ManyToOne
	@MapsId("sapi_proyecto_codigo")
	@JoinColumn(name = "SAPI_PROYECTO_CODIGO", referencedColumnName = "SAP_CODIGO")
	private SUBT_APU_PROYECTO subt_apu_proyecto;
	
	
	
	@ManyToOne
	@MapsId("sapi_iva_codigo")
	@JoinColumn(name = "SAPI_IVA_CODIGO", referencedColumnName = "SAI_CODIGO")
	private SUBT_APU_IVA subt_apu_iva;
	
}
