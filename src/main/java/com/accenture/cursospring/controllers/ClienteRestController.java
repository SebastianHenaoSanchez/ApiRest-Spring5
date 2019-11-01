package com.accenture.cursospring.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.cursospring.models.entity.Cliente;
import com.accenture.cursospring.models.services.IClienteService;

//se usa para permitir de donde me hacen las peticiones, en este caso ponemos la de angular
@CrossOrigin(origins= {"http://localhost:4200"},methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController//usamos esta notación porque es una apirest
@RequestMapping("/api")//mapeamos el rest controller para generar la url
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll(); 
	}
	
	
	
	
	@GetMapping("clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la DB");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" No existe en la base de datos!")));
			response.put("codigo:", "001");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
					
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	
	}




	@PostMapping("/clientes")
	
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNew = clienteService.save(cliente);
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la DB");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente:", clienteNew);
		response.put("codigo:", "002");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	
	
	@PutMapping("clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>(); 
		
		Cliente clienteActual = clienteService.findById(id);
		
		Cliente clienteUpdated = null;
		
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat(" No existe en la base de datos!")));
			response.put("codigo:", "001");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
					
		}
		try {
			
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			
			clienteUpdated = clienteService.save(clienteActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar  en la DB");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente:", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
		
		 
	}
	
	
	
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			clienteService.delete(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la DB");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
}