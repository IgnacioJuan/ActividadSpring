package com.guachiproject.app.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guachiproject.app.entity.Usuario;
import com.guachiproject.app.repository.UsuarioRepositorio;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	public UsuarioRepositorio usuarioRepositorio;
	@Autowired
	public S3Service s3Service;
	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		return usuarioRepositorio.findAll()
				.stream()
				.peek(curso -> curso.setCedulaUrl(s3Service.getObjextUrl(curso.getCedulapath())))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepositorio.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		// TODO Auto-generated method stub
		usuarioRepositorio.save(user);
		user.setCedulaUrl(s3Service.getObjextUrl(user.getCedulapath()));
		user.setFotoUrl(s3Service.getObjextUrl(user.getFotopath()));
		return user;
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		usuarioRepositorio.deleteById(id);
	}
	
}
