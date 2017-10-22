package com.originmobi.boteco.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.originmobi.boteco.filter.ClienteFilter;
import com.originmobi.boteco.model.Cliente;
import com.originmobi.boteco.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clientes;

	public void cadastrar(Cliente cliente) {
		clientes.save(cliente);
	}

	public List<Cliente> listar() {
		List<Cliente> todosClientes = new ArrayList<Cliente>();
		todosClientes = clientes.findAll();
		return todosClientes;
	}
	
	public void excluir(Long codigo){
		clientes.delete(codigo);
	}
	
	public List<Cliente> filter(ClienteFilter filter){
		String nome = filter.getNome() == null ? "%" : filter.getNome();
		return clientes.findByNomeContaining(nome);
	}
}
