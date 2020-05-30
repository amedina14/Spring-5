package com.bolsadeideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista = Arrays.asList(
				new Pais (1, "PT", "Portugal"),
				new Pais (2, "IT", "Italia"),
				new Pais (3, "ES", "España"),
				new Pais (4, "FR", "France"),
				new Pais (5, "DE", "Germania"),
				new Pais (6, "GB", "England"));
	}
	
	@Override
	public List<Pais> listar() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {

		Pais resultado = null;
		
		for(Pais pais: this.lista)
		
			if(id == pais.getId()) {
				resultado = pais;
				break;
			}
				
		return resultado;
	}

}
