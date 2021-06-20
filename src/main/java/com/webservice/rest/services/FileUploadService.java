package com.webservice.rest.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webservice.rest.dto.FileUploadDTO;
import com.webservice.rest.models.HashShaModel;
import com.webservice.rest.utils.pdf.InsertHeaderAndFooter;

@Service
public class FileUploadService {
	// caminho onde os arquivos originais serão carregados;
	public static String uploadDirectoryTmp = System.getProperty("user.dir")+"/uploads/tmp";
	// caminho onde os arquivos com rodapé serão gravados;
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

	public FileUploadDTO upload(String cpf, MultipartFile pdf) throws IllegalArgumentException, IllegalAccessException {

		FileUploadDTO hash = new FileUploadDTO(new HashShaModel(cpf, pdf.getOriginalFilename()));
		String hashResult = hash.getHashResult();
		
		Path fileNameAndPathSrc = Paths.get(uploadDirectoryTmp, pdf.getOriginalFilename());
		Path fileNameAndPathDest = Paths.get(uploadDirectory, pdf.getOriginalFilename());
		
		try {
			// salvando arquivo carregado em pasta temporária;
			Files.write(fileNameAndPathSrc, pdf.getBytes());
			
			// recriando arquivo com hash no rodapé;
			new InsertHeaderAndFooter().addFooter(fileNameAndPathSrc.toString(), fileNameAndPathDest.toString(), hashResult);
			
			// removendo arquivo carregado da pasta temporária;
			Files.delete(fileNameAndPathSrc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// retornando objeto DTO que será usado como resposta da requisição POST;
		return hash;
	}
}
