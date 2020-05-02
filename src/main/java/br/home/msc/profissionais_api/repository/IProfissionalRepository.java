package br.home.msc.profissionais_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.home.msc.profissionais_api.models.Profissional;

public interface IProfissionalRepository extends JpaRepository<Profissional, Integer>{

	
	Profissional findById( int id );
	
	
}
