package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.Producto;

/* Estos son los metodos del contrato. */

public interface IClienteService {

	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente cliente);

	public Cliente findOne(Long id);

	public Cliente fetchByIdWithFacturas(Long id);
	
	public void delete(Long id);

	/* Aqui implementamos un servicio dentro del servicio principal. */
	public List<Producto> findByNombre(String term);

	/*
	 * Metodo de la interfaz: A la interfaz principal se le implementa el metodo que
	 * guarda la factura. A su ves este metodo tendrà una clase de implementacion de
	 * los metodos Crud. Tambien se tiene que implementar en la clase
	 * ClienteServiceImpl.
	 */
	public void saveFactura(Factura factura);
	
	/* Metodo para buscar el producto por el ID */
	public Producto findProductoById(Long id);
	
	/* Metodo para buscar el detalle de la factura */
	public Factura findFacturaById(Long id);
	
	/* Eliminar Fctura */
	public void deleteFactura(Long id);
	
	/* Seguno paso */
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id);
	
}
