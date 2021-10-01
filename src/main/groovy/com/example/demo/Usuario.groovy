package com.example.demo

import groovy.transform.CompileStatic

import javax.persistence.*

@CompileStatic
@Entity
class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	@Column(nullable = false)
	String nombre

	@Column(nullable = false)
	String apellido

	@Column(nullable = false)
	String email

	@OneToMany(mappedBy = "usuario")
	Set<Voto> votos = []
}
