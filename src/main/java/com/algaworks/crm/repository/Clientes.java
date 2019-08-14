package com.algaworks.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.crm.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

	// select c from Cliente where c.id = :id and c.tenant = :tenant
	Cliente findByIdAndTenant(Long id, String tenant);

	// select c from Cliente where c.tenant = :tenant
	List<Cliente> findAllByTenant(String tenant);
}
