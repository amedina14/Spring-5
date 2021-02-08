package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

//public interface IClienteDao extends crudRepository<Cliente, Long>{
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	/*
	 * inner join = join fetch: para cuando ambas tablas tengan valores.
	 * 
	 * left join fetch: para cuando cliente tenga o no tenga factura. Centra la
	 * consulta en la izquierda, cliente.
	 * 
	 * Si se usa la palabra clave fetch en la consulta con el inner join se carga todo
	 * dentro de la misma consulta, por lo tanto no hay carga perezosa, si es sin
	 * fetch es con carga perezosa (await) y mejora la rapidez.
	 */
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1 ")
	public Cliente fetchByIdWithFacturas(Long id);

	/*
	 * public List<Cliente> findAll();
	 * 
	 * public void save(Cliente cliente);
	 * 
	 * public Cliente findOne(Long id);
	 * 
	 * public void delete(Long id);
	 */

}
