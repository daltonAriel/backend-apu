package com.apu_api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.apu_api.entity.enums.ListaRoles;

import lombok.Data;

@Entity
@Table(name = "SUBT_APU_ROL")
@Data
public class SUBT_APU_ROL {
	
	@Column(name = "SAR_CODIGO")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sar_codigo;
	
	
	@Column(name = "SAR_DESCRIPCION", nullable = false)
	@NotBlank
	@Enumerated(EnumType.STRING)
	private ListaRoles sar_descripcion;
	
}
