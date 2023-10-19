package com.gustavo.apirest.controller;

import com.gustavo.apirest.model.entity.Cliente;
import com.gustavo.apirest.model.payload.ResponseObject;
import com.gustavo.apirest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        ResponseEntity response;
        try {
            Cliente saveClient = clienteService.save(cliente);
            response = new ResponseEntity<>(new ResponseObject("El cliente se ha insertado correctamente.", saveClient), HttpStatus.CREATED);
        } catch (DataAccessException exDt) {
            response = new ResponseEntity(new ResponseObject(exDt.getLocalizedMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping(path = "cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Cliente cliente) {
        ResponseEntity response;
        try {
            if (clienteService.existById(cliente.getIdCliente())) {
                Cliente saveClient = clienteService.save(cliente);
                response = new ResponseEntity<>(new ResponseObject("El cliente se ha actualizado correctamente.", saveClient), HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>(new ResponseObject("El cliente que se pretende actualizar no existe.", null), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            response = new ResponseEntity(new ResponseObject(exDt.getLocalizedMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping(path = "cliente")
    public ResponseEntity delete(@RequestParam(name = "id") Integer id) {
        ResponseEntity response;
        try {
            clienteService.delete(clienteService.findById(id));
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            response = new ResponseEntity(new ResponseObject(exDt.getLocalizedMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping(path = "cliente/{id}")
    public ResponseEntity showById(@PathVariable Integer id) {
        ResponseEntity response;
        try {
            if (!clienteService.existById(id)) {
                response = new ResponseEntity<>(new ResponseObject("No ha sido posible encontrar el cliente solicitado", null), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                response = new ResponseEntity<>(new ResponseObject("Cliente encontrado", clienteService.findById(id)), HttpStatus.OK);
            }
        } catch (DataAccessException exDt) {
            response = new ResponseEntity(new ResponseObject(exDt.getLocalizedMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping(path = "clientes")
    public ResponseEntity showAll() {
        ResponseEntity response;
        try {
            List<Cliente> listData = clienteService.getAll();
            if(listData == null || listData.isEmpty()){
                response = new ResponseEntity<>(new ResponseObject("No existen clientes para consultar", null), HttpStatus.OK);
            }else {
                response = new ResponseEntity<>(new ResponseObject("", clienteService.getAll()), HttpStatus.OK);
            }
        } catch (DataAccessException exDt) {
            response = new ResponseEntity(new ResponseObject(exDt.getLocalizedMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
