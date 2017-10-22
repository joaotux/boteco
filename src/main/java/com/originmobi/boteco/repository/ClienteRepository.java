package com.originmobi.boteco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.originmobi.boteco.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public List<Cliente> findByNomeContaining(String nome);

}
