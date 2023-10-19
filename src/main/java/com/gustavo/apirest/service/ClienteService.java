package com.gustavo.apirest.service;

import com.gustavo.apirest.model.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> getAll();

    Cliente save(Cliente cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existById(Integer id);


}
