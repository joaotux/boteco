package com.originmobi.boteco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.originmobi.boteco.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
