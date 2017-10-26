package com.originmobi.boteco.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.originmobi.boteco.enumerados.ComandaStatus;

@Entity
public class Comanda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private Double credito;
	private Double debito;

	@Enumerated(EnumType.STRING)
	private ComandaStatus status;
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "mesa_id")
	private Mesa mesa;

	@ManyToMany
	@JoinTable(name = "comanda_produtos")
	private List<Produto> produto;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public Double getDebito() {
		return debito;
	}

	public void setDebito(Double debito) {
		this.debito = debito;
	}

	public ComandaStatus getStatus() {
		return status;
	}

	public void setStatus(ComandaStatus status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

}
