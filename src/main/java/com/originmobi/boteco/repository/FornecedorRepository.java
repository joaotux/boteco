package com.originmobi.boteco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.originmobi.boteco.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	public List<Fornecedor> findByNomeContaining(String nome);

}
