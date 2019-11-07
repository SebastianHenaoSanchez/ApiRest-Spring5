package com.accenture.cursospring.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.accenture.cursospring.models.entity.Cliente;

//implementamos esta interface para el manejo de los datos, la clase CrudRepository 
//nos provee los metodos conocidos para crear,borrar, editar, listar, etc

public interface IClienteDao extends JpaRepository<Cliente, Long>{

}
