package com.accenture.cursospring.models.services;

import java.util.List;

import com.accenture.cursospring.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
}
