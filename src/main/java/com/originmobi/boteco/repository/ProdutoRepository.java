package com.originmobi.boteco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.originmobi.boteco.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findByDescricaoContaining(String descricao);

}
