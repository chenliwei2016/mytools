package com.chenliwei.tools.common;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class JsonTools {

	private static Logger logger = LoggerFactory.getLogger(JsonTools.class);

	protected final static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		objectMapper.setDateFormat(df);

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
	
		
		objectMapper.registerModule(javaTimeModule);
	}

	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("hiding")
	public static <T> T toObject(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T toObject(String jsonStr, Class<T> rawType, Class<?>... parameterTypes){
		JavaType javaType =  objectMapper.getTypeFactory().constructParametricType(rawType, parameterTypes);
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T toObject(String jsonStr , JavaType javaType){
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static JavaType buildJavaType(Class<?> rawType, Class<?>... parameterTypes){
		return objectMapper.getTypeFactory().constructParametricType(rawType, parameterTypes);
	}
	
	public static JavaType buildJavaType(Class<?> rawType ,JavaType javaType){
		return objectMapper.getTypeFactory().constructParametricType(rawType, javaType);
	}
	

	@SuppressWarnings("hiding")
	public static <T> Collection<T> toObject(String jsonStr, Class<? extends Collection<?>> cClass, Class<T> tClass) {
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(cClass, tClass);
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	@SuppressWarnings("hiding")
	public static <T> ArrayList<T> toArrayList(String jsonStr, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	@SuppressWarnings("hiding")
	public static <T> HashSet<T> toHashSet(String jsonStr, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(HashSet.class, clazz);
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// public static <T> List<T> toList(String jsonStr, final Class<T> clazz){
	// return objectMapper.readValue(jsonStr, new TypeReference<List<T>>() {
	// });
	// }

	public static class T {
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
		private Date date;
	}

	public static void main(String[] args) {

		T t = new T();
		t.setDate(new Date());

		System.out.println(JsonTools.toJsonString(t));

		String s = JsonTools.toJsonString(t);

		T x = JsonTools.toObject(s, T.class);

		System.out.println(x);
	}
}
