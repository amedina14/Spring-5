package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Producto;

/* Implementamos la consulta, luego implementamos el metodo en la interface IClienteService */
public interface IProductoDao extends CrudRepository<Producto, Long>{

	/* Consulta personalizada */
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
	
	/* Consulta con nomenclatura standar JPA. */
	public List<Producto> findByNombreLikeIgnoreCase(String term);
}
