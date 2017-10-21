package com.originmobi.boteco.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@NotEmpty(message = "Descrição é obrigatória")
	@Size(max = 60, message = "Quantidade máxima de 60 caracteres")
	private String descricao;

	@ManyToOne
	@JoinColumn(name="fornecedor_id")
	@NotNull(message = "Fornecedor é obrigatório")
	private Fornecedor fornecedor;

	@NumberFormat(pattern = "#,##0.00")
	private Double valorCusto;

	@NumberFormat(pattern = "#,##0.00")
	@DecimalMin(value = "0.01", message = "Valor de venda minímo de R$ 0,01")
	@NotNull(message = "Valor venda é obrigatório")
	private Double valorVenda;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="Data é obrigatória")
	private Date dataValidade;
	private Double teorAlcoolico;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Double getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(Double teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

}
