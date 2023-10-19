package com.gustavo.apirest.model.dao;

import com.gustavo.apirest.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
