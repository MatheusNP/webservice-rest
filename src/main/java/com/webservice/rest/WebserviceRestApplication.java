package com.webservice.rest;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webservice.rest.controller.FileUploadController;

@SpringBootApplication
public class WebserviceRestApplication {

	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		new File(FileUploadController.uploadDirectoryTmp).mkdir();
		SpringApplication.run(WebserviceRestApplication.class, args);
	}

}
