package com.webservice.rest.models;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class HashShaModel {

	private String cpf;
	private String fileName;
	private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	
	public HashShaModel (String cpf, String fileName) {
		this.cpf = cpf;
		this.fileName = fileName;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public String getCpf() {
		return cpf;
	}

	public String getFileName() {
		return fileName;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getHashResult() throws IllegalArgumentException, IllegalAccessException {
	    String hashResult = "";

	    // objeto hashmap que será usado para criar chave hash;
	    Map<String, Object> objMap = new HashMap<String,Object>();

	    // capturando os campos declarados da classe: cpf, fileName e timeStamp;
	    Field[] allFields = this.getClass().getDeclaredFields();
	    // percorre cada campo, incluindo na hashmap;
	    for (Field field : allFields) {
	        field.setAccessible(true);
	        Object value = field.get(this);
	        objMap.put(field.getName(), value);
	    } 

		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-512");
		    digest.reset();
		    digest.update(objMap.toString().getBytes("utf8"));
		    // gera chave hash para a hashmap dos campos da classe;
		    hashResult = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return hashResult;
	}
}
