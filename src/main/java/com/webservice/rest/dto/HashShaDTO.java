package com.webservice.rest.dto;

import com.webservice.rest.model.HashShaModel;

public class HashShaDTO {

	private String hashResult;
	
	public HashShaDTO() {
		
	}

	public HashShaDTO(String hashResult) {
		this.hashResult = hashResult;
	}

	public HashShaDTO(HashShaModel hashSha) throws IllegalArgumentException, IllegalAccessException {
		this.hashResult = hashSha.getHashResult();
	}

	public String getHashResult() {
		return hashResult;
	}

}
