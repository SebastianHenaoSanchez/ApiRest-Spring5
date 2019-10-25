package com.accenture.cursospring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.cursospring.models.entity.Cliente;
import com.accenture.cursospring.models.services.IClienteService;

//se usa para permitir de donde me hacen las peticiones, en este caso ponemos la de angular
@CrossOrigin(origins= {"http://localhost:4200"},methods= {RequestMethod.GET})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clientedao;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clientedao.findAll(); 
	}
	
	
	
}