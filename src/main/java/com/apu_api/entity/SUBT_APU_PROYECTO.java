package com.apu_api.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "SUBT_APU_PROYECTO")
@Data
public class SUBT_APU_PROYECTO {
	
	@Column(name = "SAP_CODIGO")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sap_codigo;
	
	
	@Column(name = "SAP_NOMBRE", nullable = false, length = 1000)
	@NotBlank
	private String sap_nombre;
	
	
	@Column(name = "SAP_COSTOS_INDIRECTOS", nullable = false)
	@DecimalMin(value = "0.01", inclusive = true)
	@DecimalMax(value = "100", inclusive = true)
	@NotBlank
	private BigDecimal sap_costos_indirectos;
	
	
	@Column(name = "SAP_DESCRIPCION", nullable = false, length = 1000)
	private String sap_descripcion;
	
	
	@Column(name = "SAP_FECHA_CREACION", insertable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date sap_fecha_creacion;
	
	
	@Column(name = "SAP_FECHA_FINALIZACION", nullable = true)
	private Date sap_fecha_finalizacion;
	
	
	@Column(name = "SAP_ESTADO_FINALIZACION", nullable = false)
	private boolean sap_estado_finalizacion;
	
	
	@Column(name = "SAP_USUARIO_CODIGO", nullable = false)
	private long sap_usuario_codigo;
	
	
	
	@ManyToOne
	@MapsId("SAP_USUARIO_CODIGO")
	@JoinColumn(name = "SAP_USUARIO_CODIGO", referencedColumnName = "SAU_CODIGO")
	@JsonIgnore
	private SUBT_APU_USUARIO subt_apu_usuario;
	
	
	@OneToMany(mappedBy = "subt_apu_proyecto" ,cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SUBT_APU_PROYECTO_IVA> subt_apu_proyecto_iva = new ArrayList<>();
	
	


	
	
}
