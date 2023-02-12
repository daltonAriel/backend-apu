package com.apu_api.repository.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.apu_api.entity.SUBT_APU_PROYECTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProyectoSpecification {
	
	public static Specification<SUBT_APU_PROYECTO> orderBySapCodigoDesc() {
	    return (root, query, cb) -> {
	        query.orderBy(cb.desc(root.get("sap_codigo")));
	        return null;
	    };
	}
	
	
	
	public static Specification<SUBT_APU_PROYECTO> byId(long _id) {
		return (root, query, cb) -> {
	            return cb.equal(root.get("sap_usuario_codigo"), _id);
	    };
    }
	
	
	
	public static Specification<SUBT_APU_PROYECTO> byNombreLikeIgnoreAccentAndCapitalize(String _cadena) {
		String[] names = StringUtil.stringToArray(_cadena);
        return (Root<SUBT_APU_PROYECTO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (names == null || names.length == 0 || Arrays.stream(names).allMatch(String::isBlank)) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();
            for (String name : names) {
            	if(!name.strip().equals("")){
                predicates.add(cb.like(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("sap_nombre")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), "%"+StringUtil.removeAccent(name)+"%"));
            	}
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
	
	
	
	public static Specification<SUBT_APU_PROYECTO> byDescripcionLikeIgnoreAccentAndCapitalize(String _cadena) {
		String[] names = StringUtil.stringToArray(_cadena);
        return (Root<SUBT_APU_PROYECTO> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (names == null || names.length == 0 || Arrays.stream(names).allMatch(String::isBlank)) {
                return null;
            }
            List<Predicate> predicates = new ArrayList<>();
            for (String name : names) {
            	if(!name.strip().equals("")) {
            		predicates.add(cb.like(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("sap_descripcion")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), "%"+StringUtil.removeAccent(name)+"%"));
            	}
                
            }
            return cb.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
	
	
	
	public static Specification<SUBT_APU_PROYECTO> byCreationDates(String _dateFrom, String _dateTo) {
		
		Date dateFrom = StringUtil.stringToDate(_dateFrom);
		Date dateTo = StringUtil.stringToDate(_dateTo);
		
	    return (root, query, cb) -> {
	        if (dateFrom == null && dateTo == null) {
	            return null;
	        } else if (dateFrom != null && dateTo == null) {
	            return cb.greaterThanOrEqualTo(root.get("sap_fecha_creacion"), dateFrom);
	        } else if (dateFrom == null && dateTo != null) {
	            return cb.lessThanOrEqualTo(root.get("sap_fecha_creacion"), dateTo);
	        } else {
	            return cb.between(root.get("sap_fecha_creacion"), dateFrom, dateTo);
	        }
	    };
	}
	
	
	
	public static Specification<SUBT_APU_PROYECTO> byNombreIgnoreAccentAndCapitalize(String _nombre) {
		return (root, query, cb) -> {
            return cb.equal(cb.function("lower", String.class, cb.function("translate", String.class,cb.lower(root.get("sap_nombre")),cb.literal("áàäâÁÀÄÂéèëêÉÈËÊíìïîÍÌÏÎóòöôÓÒÖÔúùüûÚÙÜÛ"),cb.literal("aaaaaaaaeeeeeeeeiiiiiiiioooooooouuuuuuuu"))), StringUtil.removeAccent(_nombre));
		};
    }
	
	
	
	public static Specification<SUBT_APU_PROYECTO> byProyectoCodigo(long _proyectoId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("sap_codigo"), _proyectoId);
		};
    }
	
	
	public static Specification<SUBT_APU_PROYECTO> byNotEqualProyectoCodigo(long _proyectoId) {
		return (root, query, cb) -> {
			return cb.notEqual(root.get("sap_codigo"), _proyectoId);
		};
    }
	
}
