package com.webservice.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@GetMapping
	public String findAll() {
		
		return "Todos os clientes!";
	}

	@PostMapping("/save")
	public String save() {
		
		return "Cliente salvo!";
	}
}
