package com.algaworks.crm.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.crm.model.Cliente;
import com.algaworks.crm.repository.Clientes;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private Clientes clientes;

	@Transactional
	public Cliente criar(String tenant, Cliente cliente) {
		cliente.setTenant(tenant);
		
		return clientes.save(cliente);
	}

	@Transactional
	public Cliente atualizar(Long id, String tenant, Cliente cliente) {
		Cliente clienteSalvo = clientes.findByIdAndTenant(id, tenant);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id", "tenant");
		
		return clientes.save(clienteSalvo);
	}
}