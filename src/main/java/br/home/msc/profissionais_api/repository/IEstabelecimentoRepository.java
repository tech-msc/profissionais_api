package br.home.msc.profissionais_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.home.msc.profissionais_api.models.Estabelecimento;

public interface IEstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {

	Estabelecimento findById(int id);

}
