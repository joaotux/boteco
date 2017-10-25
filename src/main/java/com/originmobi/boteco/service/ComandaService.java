package com.originmobi.boteco.service;

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

}
