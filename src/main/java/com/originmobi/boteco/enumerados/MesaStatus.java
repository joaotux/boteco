package com.originmobi.boteco.enumerados;

public enum MesaStatus {
	
	LIVRE ("Livre"),
	OCUPADA ("Ocupada");

	private String descricao;
	
	private MesaStatus(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
