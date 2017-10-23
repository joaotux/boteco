package com.originmobi.boteco.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.originmobi.boteco.model.Mesa;
import com.originmobi.boteco.repository.MesaRepository;

@Service
public class MesaService {
	
	@Autowired
	private MesaRepository mesas;
	
	public void cadastrar(Mesa mesa){
		mesas.save(mesa);
	}
	
	public List<Mesa> lista(){
		List<Mesa> todasMesas = new ArrayList<Mesa>();
		todasMesas = mesas.findAll();
		return todasMesas;
	}
	
	public void excluir(Long codigo){
		mesas.delete(codigo);
	}

}
