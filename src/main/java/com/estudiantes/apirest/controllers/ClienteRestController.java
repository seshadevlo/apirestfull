package com.estudiantes.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudiantes.apirest.models.entity.Cliente;
import com.estudiantes.apirest.models.services.ClienteServiceImpl;
import com.estudiantes.apirest.models.services.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> allCustomers() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/{id}")
	public Optional<Cliente> getCustomersById(@PathVariable Long id) {
		Optional<Cliente> optionalCliente = clienteService.findById(id);
		if (!optionalCliente.isPresent())
			throw new ClienteNotFoundException("no existe un cliente con ese id");
		return optionalCliente;
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	@PostMapping("/clientes/{id}")
	public Optional<Cliente>update (@RequestBody Cliente cliente, @PathVariable Long id){
		Optional<Cliente> clienteActual = clienteService.findById(id);
		
		if(!clienteActual.isPresent()) throw new ClienteNotFoundException("no existe cliente con ese id");
		
		clienteActual.get().setNombre(cliente.getNombre());
		clienteActual.get().setApellido(cliente.getApellido());
		clienteActual.get().setEmail(cliente.getEmail());
		
		clienteService.save(clienteActual.get());
		
		return clienteActual;
	}
	@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	}

	class ClienteNotFoundException extends RuntimeException{
		public ClienteNotFoundException(String message) {
			super(message);
		}
	}
