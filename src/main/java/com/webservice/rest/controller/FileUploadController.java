package com.webservice.rest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webservice.rest.dto.HashShaDTO;
import com.webservice.rest.model.HashShaModel;
import com.webservice.rest.util.pdf.InsertHeaderAndFooter;

@RestController
public class FileUploadController {
	public static String uploadDirectoryTmp = System.getProperty("user.dir")+"/uploads/tmp";
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

	@RequestMapping(
		value = "/upload",
		method = RequestMethod.POST,
		consumes = {"multipart/form-data"},
		produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	@ResponseBody
	public HashShaDTO upload(
		@RequestParam("cpf") String cpf,
		@RequestParam("pdf") MultipartFile pdf
	) throws IllegalArgumentException, IllegalAccessException {

		HashShaDTO hash = new HashShaDTO(new HashShaModel(cpf, pdf.getOriginalFilename()));
		String hashResult = hash.getHashResult();
		
		Path fileNameAndPathSrc = Paths.get(uploadDirectoryTmp, pdf.getOriginalFilename());
		Path fileNameAndPathDest = Paths.get(uploadDirectory, pdf.getOriginalFilename());
		
		try {
			Files.write(fileNameAndPathSrc, pdf.getBytes());
			
			InsertHeaderAndFooter a = new InsertHeaderAndFooter();
			a.addFooter(fileNameAndPathSrc.toString(), fileNameAndPathDest.toString(), hashResult);
			
			Files.delete(fileNameAndPathSrc);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hash;
	}
}
