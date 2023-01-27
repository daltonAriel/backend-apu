package com.apu_api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;

import lombok.Data;

@Entity
@Table(name = "SUBT_APU_IVA")
@Data
public class SUBT_APU_IVA {
	
	
	@Column(name = "SAI_CODIGO")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sai_codigo;
	
	
	@Column(name = "SAI_PORCENTAJE", nullable = false)
	@DecimalMin(value = "0.01" , message = "El valor minimo es 0.01")
	@Max(value = 100, message = "El valor maximo es 100")
	private BigDecimal sai_porcentaje;
	
	
	@Column(name = "SAI_FECHA_CREACION", nullable = false, insertable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date sai_fecha_creacion;
	
	
	@Column(name = "SAI_FECHA_EXPIRACION", nullable = false)
	private Date sai_fecha_expiracion;
	
	
	@Column(name = "SAI_ESTADO", nullable = false)
	private Date sai_estado;
	
	
}
