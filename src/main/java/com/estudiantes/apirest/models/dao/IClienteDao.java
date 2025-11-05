package com.estudiantes.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.estudiantes.apirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
