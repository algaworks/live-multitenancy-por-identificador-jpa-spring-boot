package com.algaworks.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.crm.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {
	
	Cliente findByIdAndTenant(Long id, String tenant);
	
	List<Cliente> findAllByTenant(String tenant);
}
