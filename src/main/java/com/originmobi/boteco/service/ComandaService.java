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

	public String adicionaProdutoComanda(Long codigo_comanda, Long codigo_produto) {
		Comanda comanda = pesquisaComandaId(codigo_comanda);
		Produto produto = produtos.pesquisaId(codigo_produto);

		if (ComandaStatus.CANCELADA.equals(comanda.getStatus())) {
			return "Comanda cancelada";
		}

		if (comanda.getCredito() == null)
			return "Crédito null";

		if (comanda.getCredito() >= produto.getValorVenda()) {
			Double creditoAtual, debitoAtual, valorProduto;

			valorProduto = produto.getValorVenda();
			creditoAtual = comanda.getCredito();
			debitoAtual = comanda.getDebito() == null ? 0.00 : comanda.getDebito();

			comanda.setCredito(creditoAtual - valorProduto);
			comanda.setDebito(debitoAtual + valorProduto);
			comanda.getProduto().add(produto);

			cadastrar(comanda);

			return "ok";
		} else {
			return "credito insuficiente";
		}
	}

	public String creditar(Long codigo_comanda, Double credito) {
		
		Comanda comanda = comandas.findOne(codigo_comanda);

		if (ComandaStatus.CANCELADA.equals(comanda.getStatus())) {
			return "Comanda cancelada";
		}
		
		Double creditoAtual = comanda.getCredito();

		comanda.setCredito(creditoAtual + credito);

		comandas.save(comanda);
		
		return "ok";
	}

	public void cancelar(Long codigo) {
		Comanda comanda;

		comanda = comandas.findOne(codigo);

		comanda.setStatus(ComandaStatus.CANCELADA);
		comandas.save(comanda);
	}

}
