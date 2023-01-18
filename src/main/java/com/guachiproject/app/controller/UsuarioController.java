package com.guachiproject.app.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guachiproject.app.entity.Usuario;
import com.guachiproject.app.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	// Crear
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}

	// busqueda
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userid) {
		Optional<Usuario> oUsuario = usuarioService.findById(userid);
		if (!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUsuario);
	}

	// Actualizar
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario c) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			try {
				// BeanUtils.copyProperties(c, usuario.get());
				usuario.get().setEmail(c.getEmail());
				usuario.get().setEstado(c.isEstado());
				usuario.get().setNombre(c.getNombre());
				return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

	}

	// Eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long iduser) {
		if (!usuarioService.findById(iduser).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	//Listar
	@GetMapping
	public java.util.List<Usuario> readall(){
		java.util.List<Usuario> users= StreamSupport
				.stream(usuarioService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return users;
	}
}
