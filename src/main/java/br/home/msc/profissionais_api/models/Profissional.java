package br.home.msc.profissionais_api.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profissional")
public class Profissional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;

	@ManyToOne(optional = true)
	@JoinColumn(name = "estabelecimento_id", referencedColumnName = "id", nullable = true)
	private Estabelecimento estabelecimento_id;

	public Integer getEstabelecimento_id() {
		if (estabelecimento_id == null) {
			return null;
		}
		return estabelecimento_id.getId();
	}

	public void setEstabelecimento_id(Estabelecimento estabelecimento_id) {
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

	public Profissional(String nome, String endereco, Estabelecimento estabelecimento) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.estabelecimento_id = estabelecimento;
	}

	public Profissional() {
	}

}
