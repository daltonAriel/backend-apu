package com.apu_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apu_api.entity.SUBT_APU_PROYECTO_IVA;
import com.apu_api.entity.embeddedId.ProyectoIvaCodigo;

@Repository
public interface ProyectoIvaRepository extends JpaRepository<SUBT_APU_PROYECTO_IVA, ProyectoIvaCodigo>{
	
	
	
	
}
