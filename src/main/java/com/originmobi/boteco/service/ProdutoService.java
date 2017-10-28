package com.originmobi.boteco.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.originmobi.boteco.filter.ProdutoFilter;
import com.originmobi.boteco.model.Produto;
import com.originmobi.boteco.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtos;

	public List<Produto> listar() {
		List<Produto> listaProdutos = new ArrayList<Produto>();
		listaProdutos = produtos.findAll();
		return listaProdutos;
	}

	public void cadastrar(Produto produto) {
		try {
			produtos.save(produto);
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar produto: " + e);
		}
	}

	public void excluir(Long codigo) {
		produtos.delete(codigo);
	}

	public List<Produto> filter(ProdutoFilter filter) {
		String descricao = filter.getDescricao() == null ? "%" : filter.getDescricao();
		return produtos.findByDescricaoContaining(descricao);
	}
	
	public Produto pesquisaId(Long codigo) {
		return produtos.findOne(codigo);
	}

}
