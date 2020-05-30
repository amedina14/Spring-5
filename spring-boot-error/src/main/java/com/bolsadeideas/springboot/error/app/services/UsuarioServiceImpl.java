package com.bolsadeideas.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;
	
	
	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1, "Adrian", "Rodriguez"));
		this.lista.add(new Usuario(2, "Pepa", "Due"));
		this.lista.add(new Usuario(3, "Pol", "Tre"));
		this.lista.add(new Usuario(4, "Arianna", "Quattro"));
		this.lista.add(new Usuario(5, "Marie", "Cinque"));
		this.lista.add(new Usuario(6, "Sofi", "Sei"));
		this.lista.add(new Usuario(7, "Me", "Sette"));
	}

	@Override
	public List<Usuario> listar() {
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		
		for(Usuario u: this.lista) {
			if(u.getId().equals(id)) {
				resultado = u;
				break;
			}
		}
		return resultado;
	}

	@Override
	public Optional<Usuario> obtenerPorIdOptional(Integer id) {
		Usuario usuario = this.obtenerPorId(id);		
		return Optional.ofNullable(usuario);
	}

}
