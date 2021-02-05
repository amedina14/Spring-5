package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

/*Registra como componente bean esta interface*/
@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

	/*
	 * Inyecta de forma automatica el EM segun la config de la Unidad de
	 * persistencia que contiene el data source(DB), proveerdor JPA (properties). Si
	 * no hay la app usa H2
	 */
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public void save(Cliente cliente) {
		if (cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		} else {
			em.persist(cliente);
		}
	}

	@Override
	public void delete(Long id) {
//		Cliente cliente = findOne(id);
		em.remove(findOne(id));

	}
}
