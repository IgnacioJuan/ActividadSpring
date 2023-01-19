package com.guachiproject.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "email")
	private String email;
	@Column(name = "estado")
	private boolean estado;
	
	//Imagenes
	
	@Column(name = "foto")
	private String fotopath;
	@Column(name = "cedula")
	private String cedulapath;
	
	@Transient
	private String fotoUrl;
	@Transient
	private String cedulaUrl;
}
