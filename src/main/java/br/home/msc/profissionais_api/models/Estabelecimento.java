package br.home.msc.profissionais_api.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String endereco;

	@OneToMany(mappedBy = "estabelecimento_id", targetEntity = Profissional.class, cascade = CascadeType.DETACH)
	private Collection<Profissional> profissionais;

//	@PreRemove
//	private void preRemove() {
//		for (Profissional p : profissionais) {
//			p.setEstabelecimento_id(null);
//		}
//	}

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

	public Estabelecimento(String nome, String endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
	}

	public Estabelecimento() {
	}

	public Estabelecimento(int id) {
		this.setId(id);
	}

//	public Collection<Profissional> getProfissionais() {
//		return profissionais;
//	}

	public void setProfissionais(Collection<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

}
