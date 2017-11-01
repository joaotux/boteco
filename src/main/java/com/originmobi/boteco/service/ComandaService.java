package com.originmobi.boteco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.originmobi.boteco.enumerados.ComandaStatus;
import com.originmobi.boteco.model.Comanda;
import com.originmobi.boteco.model.Produto;
import com.originmobi.boteco.repository.ComandaRepository;

@Service
public class ComandaService {

	@Autowired
	private ComandaRepository comandas;

	@Autowired
	private ProdutoService produtos;

	public void cadastrar(Comanda comanda) {
		comandas.save(comanda);
	}

	public List<Comanda> lista() {
		return comandas.findAll();
	}

	public void excluir(Long codigo) {
		comandas.delete(codigo);
	}

	private Comanda pesquisaComandaId(Long codigo) {
		return comandas.findOne(codigo);
	}

	/*
	 * Responsável por adicionar os produtos em uma comanda.
	 * 
	 */
	public String adicionaProdutoComanda(Long codigo_comanda, Long codigo_produto) {
		Comanda comanda = pesquisaComandaId(codigo_comanda);
		Produto produto = produtos.pesquisaId(codigo_produto);

		if (ComandaStatus.CANCELADA.equals(comanda.getStatus())) {
			System.out.println("Comanda cancelada");
			return "Comanda cancelada";
		}
		
		if (comanda.getCredito() == null)
			return "Crédito null";

		if (comanda.getCredito() >= produto.getValorVenda()) {
			Double creditoAtual, debitoAtual, valorProduto;
			
			valorProduto = produto.getValorVenda();
			creditoAtual = comanda.getCredito();
			debitoAtual = comanda.getDebito() == null ? 0.00 : comanda.getDebito();
			
			System.out.println("Valor de crédito suficiente!");

			System.out.println("credito comanda " + comanda.getCredito());
			System.out.println("valor produto " + produto.getValorVenda());

			comanda.setCredito(creditoAtual - valorProduto);
			comanda.setDebito(debitoAtual + valorProduto);
			comanda.getProduto().add(produto);
			
			cadastrar(comanda);
			
			return "ok";
		} else {
			return "credito insuficiente";
		}
	}

}
