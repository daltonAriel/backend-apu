package com.apu_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.apu_api.entity.SUBT_APU_UNIDAD;

@Repository
public interface UnidadRepository extends JpaRepository<SUBT_APU_UNIDAD, Long>, JpaSpecificationExecutor<SUBT_APU_UNIDAD>{

}
