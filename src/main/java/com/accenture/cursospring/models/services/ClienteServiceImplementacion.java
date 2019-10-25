package com.accenture.cursospring.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.cursospring.models.dao.IClienteDao;
import com.accenture.cursospring.models.entity.Cliente;



@Service//demarcamos la clase como service para que sea un componente del bin
public class ClienteServiceImplementacion implements IClienteService{

	@Autowired /*inyección de dependencias, vamos a inyectar el cliente DAO que es donde
	estan los metodos para CRUD*/
	private IClienteDao clientedao;
	
	@Override
	@Transactional(readOnly=true)//para que el metodo sea de solo lectura
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clientedao.findAll();
	}

}
