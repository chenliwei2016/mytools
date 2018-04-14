package com.chenliwei.tools.common;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Java8DateTools {
	
	public static   LocalDateTime parse(String text , String pattern){ 
		if (StringTools.isNullOrEmpty(text)  ){
			return null;
		}
		
		return LocalDateTime.parse(text,DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalDateTime convert(LocalDate localDate){
		return LocalDateTime.of(localDate, LocalTime.of(0, 0, 0));
	}
	
	public static String toStirng(LocalDateTime date ,String pattern){
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String curDate(String pattern){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	
	
//	public static void main(String[] args){
//		LocalDateTime date = parse("20180319120101","yyyyMMddHHmmss");
//		System.out.println(date);
//	}

}
