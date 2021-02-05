package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteCrudRepo extends CrudRepository<Cliente, Long>{

	/*
	 * Se si estende da CrudRepository<T, ID>
	 * Non c'è più bisogno di un implementazione concreta del servizio
	 * infrastrutturale perchè al suo interno possiede già i metodi
	 * che accedono al DB.
	 * 
	 * https://docs.spring.io/spring-data/jpa/docs/2.4.3/reference/html/#repositories.core-concepts
	 * https://docs.spring.io/spring-data/jpa/docs/2.4.3/reference/html/#jpa.query-methods
	 * https://docs.spring.io/spring-data/jpa/docs/2.4.3/reference/html/#jpa.query-methods.at-query
	 * */
}
