package com.bolsadeideas.springboot.app.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteCrudRepo;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Service
public class ClienteCrudRepoServiceImpl implements IClienteCrudRepoService{

	@Autowired
	private IClienteCrudRepo clienteCrudRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll(){
		return (List<Cliente>) clienteCrudRepo.findAll();
	}
	
	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteCrudRepo.save(cliente);
		//clienteCrudRepo.findOne(cliente);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		Cliente cliente = null;
		// Optional<T> avvolge il risultato della query e fornisce altri metodi. Si deve scegliere uno.
		cliente = clienteCrudRepo.findById(id).orElse(null);
		return cliente;
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		clienteCrudRepo.deleteById(id);
	}
}


/*
 * https://docs.spring.io/spring-data/jpa/docs/2.4.3/reference/html/#jpa.query-methods.query-creation
 * */
