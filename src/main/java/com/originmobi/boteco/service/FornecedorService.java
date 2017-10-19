package com.originmobi.boteco.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.originmobi.boteco.model.Fornecedor;
import com.originmobi.boteco.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedores;

	public List<Fornecedor> listar() {
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		lista = fornecedores.findAll();
		return lista;
	}
	
	public void salvar(Fornecedor fornecedor){
		fornecedores.save(fornecedor);
	}

}
