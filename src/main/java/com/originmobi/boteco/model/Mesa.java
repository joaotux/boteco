package com.originmobi.boteco.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.originmobi.boteco.enumerados.MesaStatus;

@Entity
public class Mesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@NotEmpty(message = "Descrição é obrigatória")
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private MesaStatus mesaStatus;

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

	public MesaStatus getMesaStatus() {
		return mesaStatus;
	}

	public void setMesaStatus(MesaStatus mesaStatus) {
		this.mesaStatus = mesaStatus;
	}

}
