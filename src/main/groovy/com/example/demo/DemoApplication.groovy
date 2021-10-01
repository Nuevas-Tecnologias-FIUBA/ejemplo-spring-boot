package com.example.demo

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

import javax.persistence.EntityManager

interface UsuarioService {
	void crearUsuario()
}

@CompileStatic
@Service
class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	EntityManager entityManager

	@Override
	@Transactional
	void crearUsuario() {
		(1..10).each {
			def usuario = new Usuario(nombre: it, apellido: it, email: it)
			entityManager.persist(usuario)

			def voto = new Voto(usuario: usuario)
			entityManager.persist(voto)
		}
	}
}

@RestController
@CompileStatic
class PruebaController {

	@Autowired
	UsuarioService usuarioService
	@Autowired
	EntityManager entityManager

	// método http
	// GET
	// POST
	// PUT
	// PATCH
	// DELETE

	@GetMapping("/")
	def index() {
		[hola: 1, chau: 2]
	}

	@GetMapping("/crear-usuarios")
	def crearUsuarios() {
		usuarioService.crearUsuario()
		return [todoLegal: true]
	}

	@GetMapping("/get-usuario")
	def getUsuarios(Long id) {
		def u = entityManager.find(Usuario, id)
		[
			id: u.id,
			nombre: u.nombre,
			cantidadVotos: u.votos.size(),
		]
	}
}

@Controller
@CompileStatic
class HtmlController {

	@GetMapping("/dame-html")
	def index() {
		new ModelAndView("/index", [
			usuarios: [
				[id: 1],
				[id: 4],
				[id: 3],
				[id: 2],
				[id: 111],
			],
		])
	}
}

@SpringBootApplication
class DemoApplication {
	static void main(String[] args) {
		SpringApplication.run(DemoApplication, args)
	}
}
