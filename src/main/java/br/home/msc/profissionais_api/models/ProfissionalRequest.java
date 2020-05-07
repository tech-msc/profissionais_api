package br.home.msc.profissionais_api.models;

import java.io.Serializable;

public class ProfissionalRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String endereco;
	private Integer estabelecimento_id;

	public Integer getEstabelecimento_id() {
		return estabelecimento_id;
	}

	public void setEstabelecimento_id(int estabelecimento_id) {
		this.estabelecimento_id = estabelecimento_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ProfissionalRequest(String nome, String endereco, Integer estabelecimento) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.estabelecimento_id = estabelecimento;
	}

	public ProfissionalRequest() {
		// TODO Auto-generated constructor stub
	}

}