package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Factura;

/* 
 * Implementamos el objeto de acceso a datos Factura, que ahora serà una interface IFacturaDao
 * Y nos provee los metodos CRUD para manipular los objetos en el DB.
 * Luego se implementa el metodo de la interface en la interface principal IClienteServive con 
 * el objeto interface Factura. CrudRepository recibe un tipo de dato generico, la clase "Factura", y el tipo 
 * de dato de la PK de la clase tipo generico." 
 */
public interface IFacturaDao extends CrudRepository<Factura, Long> {

	/*
	 * Mejor manera de hacer la consulta entre varias 
	 * realciones de entidades gracias a los join fetch.
	 * 
	 * Primer paso de la implementacion.
	 * 
	 * Agregamos un nuevo metodo para una consulta en el
	 * mismo objeto facturaDao. 
	 * Con esto mejoramos la consulta: 
	 * - Por cada factura obtiene el cliente 
	 * - Por cada factura obtiene las lineas
	 * - Por cada linea obtiene producto
	 */
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id = ?1 ")
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
