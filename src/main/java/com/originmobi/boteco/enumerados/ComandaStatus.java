package com.originmobi.boteco.enumerados;

public enum ComandaStatus {

	VALIDA("Valida"), 
	CANCELADA("Cancelada");

	private String descricao;

	private ComandaStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
