package com.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.MascotaService;

@RestController
@RequestMapping("api/mascotas")
public class MascotaController {

	@Autowired
	private MascotaService service;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> listarMascotas(){
		try {
			return service.listarMascotas();
			
		}catch(Exception e) {
			System.out.println("Error encontrado: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/test")
	public String test() {
	    return "Hola desde mascotas";
	}

	
}
