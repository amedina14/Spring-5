package com.bolsadeideas.springboot.web.app.controllers;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app")

public class IndexController {
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;
	
	@GetMapping({"/index", "/", "", "/home"})
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}
		
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Adrian");
		usuario.setApellido("Rodriguez");
		usuario.setEmail("adrian14@email.com");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		
		return "perfil";
	}
	
	/*Metodo para mostar el objeto(la tabla) en una sola vista, en la vista actual.*/
	@RequestMapping("/listar")
	public String listar(Model model) {
/*		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Adrian","Rodriguez","adrian@email.com"));
		usuarios.add(new Usuario("Lucas","George","lucas@email.com"));
		usuarios.add(new Usuario("Obi","Wan","Obi@email.com"));
		usuarios.add(new Usuario("Plo","Kun","plo@email.com"));*/

		model.addAttribute("titulo", textoListar);
//		model.addAttribute("usuarios", usuarios);		
		return "listar";
	}
	

	/*Objeto(la tabla) listo para ser usado e varias las vistas del controlador que son comunes a todos
	 * los metodos.*/
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(new Usuario("Adrian","Rodriguez","adrian@email.com"),
				new Usuario("Lucas","George","lucas@email.com"),
				new Usuario("Obi","Wan","Obi@email.com"),
				new Usuario("Plo","Kun","plo@email.com"));
		return usuarios;
	}
	
}
