package com.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Mascota;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> buscarPorCodigo(@PathVariable Long id){
		try {
			return service.buscarPorCodigo(id);
		} catch (Exception e) {
			System.out.println("Error encontrado: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/grabar")
	public ResponseEntity<Map<String, Object>> grabarMascota(@RequestBody Mascota mascota){	
		try {
			return service.grabarMascota(mascota);
		}catch(Exception e) {
			System.out.println("Error encontrado: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota){
		try {
			return service.actualizarMascota(id, mascota);
		}catch(Exception e) {
			System.out.println("Error encontrado: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> eliminarMascota(@PathVariable Long id){
		try {
			return service.eliminarMascota(id);
		}catch (Exception e) {
			System.out.println("Error encontrado" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
