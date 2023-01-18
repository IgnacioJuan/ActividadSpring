package com.guachiproject.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.guachiproject.app.entity.Usuario;

public interface UsuarioService {
	public Iterable<Usuario> findAll();
	public Page<Usuario> findAll(Pageable pageable);
	public Optional<Usuario> findById(Long id);
	public Usuario save(Usuario user);
	public void deleteById(Long id);
}
