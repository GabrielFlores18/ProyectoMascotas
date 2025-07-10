package com.springboot.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.springboot.model.Mascota;

public interface MascotaService {

	public ResponseEntity<Map<String, Object>> listarMascotas();
	public ResponseEntity<Map<String, Object>> buscarPorNombre(String nombre);
	public ResponseEntity<Map<String, Object>> grabarMascota(Mascota mascota);
	public ResponseEntity<Map<String, Object>> actualizarMascota(Long id, Mascota mascota);
	public ResponseEntity<Map<String, Object>> eliminarMascota(Long id);
	public ResponseEntity<Map<String, Object>> buscarPorCodigo(Long id);
	
	
}
