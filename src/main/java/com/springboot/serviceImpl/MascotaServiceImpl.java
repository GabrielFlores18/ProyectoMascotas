package com.springboot.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.model.Mascota;
import com.springboot.repository.MascotaRepository;
import com.springboot.service.MascotaService;

@Service
public class MascotaServiceImpl implements MascotaService{
	
	@Autowired
	private MascotaRepository repository;

	@Override
	public ResponseEntity<Map<String, Object>> listarMascotas() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Mascota> mascotas = repository.findAll();
		if(mascotas.isEmpty()) {
			response.put("mensaje", "No existen registros");
			response.put("status", HttpStatus.NOT_FOUND.value());
			response.put("mascota", mascotas);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}else {
			response.put("mensaje", "Lista de mascotas completa");
			response.put("status", HttpStatus.OK);
			response.put("mascota", mascotas);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		}catch(Exception e) {
			response.put("mensaje", "Error en el Sistema");
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("mascota", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	
	@Override
	public ResponseEntity<Map<String, Object>> buscarPorCodigo(Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Mascota> mascotaExiste = repository.findById(id);
		if(!mascotaExiste.isPresent()) {
			response.put("mensaje", "Mascota con codigo " + id + " no encontrado");
			response.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			response.put("mensaje", "Mascota con codigo " + id + " encontrado");
			response.put("mascota", mascotaExiste.get());
			response.put("status", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		}catch(Exception e) {
			response.put("mensaje", "Error en el Sistema");
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.put("mascota", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> grabarMascota(Mascota mascota) {
		Map<String, Object> response = new HashMap<>();
		Mascota nuevaMascota = repository.save(mascota);
		
		response.put("mensaje","Mascota Agregada exitosamente");
		response.put("status", HttpStatus.CREATED);
		response.put("mascota", nuevaMascota);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<Map<String, Object>> actualizarMascota(Long id, Mascota mascota) {
		Map<String, Object>response = new HashMap<>();
		Optional<Mascota> mascotaExiste = repository.findById(id);
		
		if(!mascotaExiste.isPresent()) {
			response.put("mensaje", "mascota con codigo " + id + " no encontrado");
			response.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}else {
			Mascota mascotaActualizado = mascotaExiste.get();
			mascotaActualizado.setNombre(mascota.getNombre());
			mascotaActualizado.setEdad(mascota.getEdad());
			mascotaActualizado.setRaza(mascota.getRaza());
			mascotaActualizado.setSexo(mascota.getSexo());
			mascotaActualizado.setTamanio(mascota.getTamanio());
			mascotaActualizado.setTipo(mascota.getTipo());
			
			repository.save(mascotaActualizado);
			response.put("mensaje", "Mascota Actualizada Exitosamente");
			response.put("status", HttpStatus.OK);
			response.put("mascota", mascotaActualizado);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	}

	@Override
	public ResponseEntity<Map<String, Object>> eliminarMascota(Long id) {
		Map<String, Object>response = new HashMap<>();
		Optional<Mascota> mascotaExiste = repository.findById(id);
		
		if(!mascotaExiste.isPresent()) {
			response.put("mensaje", "Mascota con codigo " + id + "no encontrado");
			response.put("status", HttpStatus.NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

		}else {
			repository.deleteById(id);
			response.put("mensaje", "Mensaje con codigo " + id + "Eliminado Exitosamente");
			response.put("status", HttpStatus.OK);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	}


	@Override
	public ResponseEntity<Map<String, Object>> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}



	
	

}
