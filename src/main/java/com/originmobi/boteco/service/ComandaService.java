package com.originmobi.boteco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.originmobi.boteco.model.Comanda;
import com.originmobi.boteco.repository.ComandaRepository;

@Service
public class ComandaService {

	@Autowired
	private ComandaRepository comandas;

	public void cadastrar(Comanda comanda) {
		comandas.save(comanda);
	}

	public List<Comanda> lista() {
		return comandas.findAll();
	}

	public void excluir(Long codigo) {
		comandas.delete(codigo);
	}

	public Comanda pesquisarId(Long codigo) {
		return comandas.findOne(codigo);
	}

}
