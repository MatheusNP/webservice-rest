package com.webservice.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webservice.rest.dto.FileUploadDTO;
import com.webservice.rest.services.FileUploadService;

@RestController
public class FileUploadController {
	public static String uploadDirectoryTmp = System.getProperty("user.dir")+"/uploads/tmp";
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

	@Autowired
	private FileUploadService service;
	
	@RequestMapping(
		value = "/upload",
		method = RequestMethod.POST,
		consumes = {"multipart/form-data"}
	)
	@ResponseBody
	public ResponseEntity<FileUploadDTO> upload(
		@RequestParam("cpf") String cpf,
		@RequestParam("pdf") MultipartFile pdf
	) throws IllegalArgumentException, IllegalAccessException {

		// cria objeto DTO com o resultado do upload do arquivo (chave hash gerada);
		FileUploadDTO fileDto = service.upload(cpf, pdf);
		
		return ResponseEntity.ok(fileDto);
	}
}
