package com.apu_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apu_api.entity.SUBT_APU_IVA;

@Repository
public interface IvaRepostory extends JpaRepository<SUBT_APU_IVA, Long>{
	
	
    @Query(value = "SELECT * FROM (SELECT * FROM SUBT_APU_IVA WHERE SAI_ESTADO = 1 AND SAI_FECHA_EXPIRACION IS NULL) WHERE ROWNUM = 1", nativeQuery = true)
	Optional<SUBT_APU_IVA> findLast();
	
	
}
