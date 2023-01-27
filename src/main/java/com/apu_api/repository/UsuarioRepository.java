package com.apu_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apu_api.entity.SUBT_APU_USUARIO;

@Repository
public interface UsuarioRepository extends JpaRepository<SUBT_APU_USUARIO, Long>{
	
	@Query(value = "SELECT sau FROM SUBT_APU_USUARIO sau WHERE sau.sau_correo = :correo")
	SUBT_APU_USUARIO findBySau_correo(@Param("correo") String correo);
	
	
}
