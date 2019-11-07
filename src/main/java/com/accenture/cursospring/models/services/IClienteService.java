package com.accenture.cursospring.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accenture.cursospring.models.entity.Cliente;


public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public Cliente findById(Long id);
	
	public void delete (Long id);
	
	public Page<Cliente> findAll(Pageable pageable);
	
}
