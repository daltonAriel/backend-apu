package com.apu_api.repository.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.apu_api.entity.SUBT_APU_UNIDAD;


public class UnidadSpecification {

	
	public static Specification<SUBT_APU_UNIDAD> orderBySapCodigoDesc() {
	    return (root, query, cb) -> {
	        query.orderBy(cb.desc(root.get("saun_abreviatura")));
	        return null;
	    };
	}
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byAbreviaturaLikeIgnoreAccentAndCapitalize(String _cadena) {
		String[] names = StringUtil.stringToArray(_cadena);
        return (Root<SUBT_APU_UNIDAD> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (names == null || names.length == 0 || Arrays.stream(names).allMatch(String::isBlank)) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();
            for (String name : names) {
            	if(!name.strip().equals("")){
                predicates.add(cb.like(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("saun_abreviatura")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), "%"+StringUtil.removeAccent(name)+"%"));
            	}
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byDescripcionLikeIgnoreAccentAndCapitalize(String _cadena) {
		String[] names = StringUtil.stringToArray(_cadena);
        return (Root<SUBT_APU_UNIDAD> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (names == null || names.length == 0 || Arrays.stream(names).allMatch(String::isBlank)) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();
            for (String name : names) {
            	if(!name.strip().equals("")){
                predicates.add(cb.like(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("saun_descripcion")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), "%"+StringUtil.removeAccent(name)+"%"));
            	}
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byEstado(Boolean _estado) {
		if(_estado == null) {
			return null;
		}
		return (root, query, cb) -> {
	            return cb.equal(root.get("saun_estado"), _estado);
	    };
    }
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byAbreviaturaIgnoreAccentAndCapitalize(String _abreviatura) {
		return (root, query, cb) -> {
            return cb.equal(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("saun_abreviatura")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), StringUtil.removeAccent(_abreviatura));
		};
    }
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byDescripcionIgnoreAccentAndCapitalize(String _descripcion) {
		return (root, query, cb) -> {
            return cb.equal(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("saun_descripcion")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), StringUtil.removeAccent(_descripcion));
		};
    }
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byUnidadCodigo(long _unidadId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("saun_codigo"), _unidadId);
		};
    }
	
	
	
	public static Specification<SUBT_APU_UNIDAD> byNotEqualUnidadCodigo(long _unidadId) {
		return (root, query, cb) -> {
			return cb.notEqual(root.get("saun_codigo"), _unidadId);
		};
    }
	
	
	
}
