package br.com.agroallium.models;

public enum Regiao {

	CENTRO_OESTE("Centro-Oeste"), NORDESTE("Nordeste"), NORTE("Norte"), SUL("Sul"), SUDESTE("Sudeste");

	private String descricao;

	Regiao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
