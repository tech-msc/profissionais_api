package br.home.msc.profissionais_api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.home.msc.profissionais_api.models.Estabelecimento;
import br.home.msc.profissionais_api.models.Profissional;
import br.home.msc.profissionais_api.repository.IEstabelecimentoRepository;
import br.home.msc.profissionais_api.repository.IProfissionalRepository;

@RestController
@RequestMapping("/api/profissional")
public class ProfissionalResource {

	@Autowired
	IProfissionalRepository profissionalRepository;

	@Autowired
	IEstabelecimentoRepository estabelecimentoRepository;

	@GetMapping()
	public ResponseEntity<List<Profissional>> listAll() {
		try {

			List<Profissional> profissional_retorno = profissionalRepository.findAll();
			return new ResponseEntity<List<Profissional>>(profissional_retorno, HttpStatus.FOUND);

		} catch (Exception e) {
		}

		return new ResponseEntity<List<Profissional>>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Profissional> listOne(@PathVariable(value = "id") int id) {
		try {

			Profissional profissional_retorno = profissionalRepository.findById(id);

			if (profissional_retorno != null) {
				return new ResponseEntity<Profissional>(profissional_retorno, HttpStatus.FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Profissional>(HttpStatus.NOT_FOUND);

	}

	@PostMapping()
	public ResponseEntity<Profissional> create(@Valid @RequestBody Profissional profissional) {
		try {
			return new ResponseEntity<Profissional>(profissionalRepository.save(profissional), HttpStatus.ACCEPTED);

		} catch (Exception e) {
			// String em = e.getMessage();
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Profissional> update(@PathVariable int id, @RequestBody Profissional profissional) {

		if(profissional.getId() != id) {
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}
		
		Estabelecimento estabelecimento = estabelecimentoRepository
				.findById((int) profissional.getEstabelecimento_id());

		return profissionalRepository.findById(Integer.valueOf(id)).map(mapper -> {
			mapper.setNome(profissional.getNome());
			mapper.setEndereco(profissional.getEndereco());
			mapper.setEstabelecimento_id(estabelecimento);
			Profissional updated = profissionalRepository.save(mapper);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Profissional> delete(@PathVariable int id) {

		Profissional profissionalExiste = profissionalRepository
				.findById(id);

		if (profissionalExiste != null) {
			profissionalRepository.deleteById(id);
			return new ResponseEntity<Profissional>(profissionalExiste, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Profissional>(HttpStatus.NOT_FOUND);
	}

}
