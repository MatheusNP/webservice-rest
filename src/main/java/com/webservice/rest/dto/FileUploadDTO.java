package com.webservice.rest.dto;

import com.webservice.rest.models.HashShaModel;

public class FileUploadDTO {

	// campos que serão enviados como resposta da requisição;
	private String hashResult;
	
	public FileUploadDTO() {
		
	}

	public FileUploadDTO(String hashResult) {
		this.hashResult = hashResult;
	}

	public FileUploadDTO(HashShaModel hashSha) throws IllegalArgumentException, IllegalAccessException {
		this.hashResult = hashSha.getHashResult();
	}

	public String getHashResult() {
		return hashResult;
	}

}
