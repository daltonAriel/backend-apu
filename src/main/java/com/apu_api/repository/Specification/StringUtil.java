package com.apu_api.repository.Specification;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class StringUtil {
	
	
	public static String removeAccent(String s) {
		if(s == null) {
			return null;
		}
	    s = s.replaceAll("[áàäâÁÀÄÂ]", "a");
	    s = s.replaceAll("[éèëêÉÈËÊ]", "e");
	    s = s.replaceAll("[íìïîÍÌÏÎ]", "i");
	    s = s.replaceAll("[óòöôÓÒÖÔ]", "o");
	    s = s.replaceAll("[úùüûÚÙÜÛ]", "u");
	    return s.toLowerCase().strip();
	}
	
	
	public static String[] stringToArray(String s) {
			if(s == null) {
				return null;
			}
			String[] array = s.split(" "); 
			array = Arrays.stream(array)
		              .filter(elem -> !elem.isBlank())
		              .toArray(String[]::new);
		    return array;
	}
	
	
	public static Date stringToDate(String s) {
		try {
			//SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = formatter.parse(s);
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static String removeExtraSpaces(String s) {
		try {
			return s.trim().replaceAll("\\s+", " ");
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
}
