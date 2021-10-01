package com.example.demo

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@CompileStatic
@Entity
class Voto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	@ManyToOne
	@JoinColumn
	Usuario usuario
}
