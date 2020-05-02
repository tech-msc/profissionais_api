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
import br.home.msc.profissionais_api.repository.IEstabelecimentoRepository;
import br.home.msc.profissionais_api.repository.IProfissionalRepository;

@RestController
@RequestMapping({"/api/estabelecimento"})
public class EstabelecimentoResource {

	@Autowired
	IProfissionalRepository profissionalRepository;

	@Autowired
	IEstabelecimentoRepository estabelecimentoRepository;

	@GetMapping
	public ResponseEntity<List<Estabelecimento>> listAll() {
		try {

			List<Estabelecimento> estabelecimento_retorno = estabelecimentoRepository.findAll();
			return new ResponseEntity<List<Estabelecimento>>(estabelecimento_retorno, HttpStatus.FOUND);

		} catch (Exception e) {
			return new ResponseEntity<List<Estabelecimento>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Estabelecimento> listOne(@PathVariable(value = "id") int id) {
		try {

			Estabelecimento estabelecimento_retorno = estabelecimentoRepository.findById(id);

			if (estabelecimento_retorno != null) {
				return new ResponseEntity<Estabelecimento>(estabelecimento_retorno, HttpStatus.FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Estabelecimento>(HttpStatus.NOT_FOUND);

	}

	@PostMapping
	public ResponseEntity<Estabelecimento> create(@Valid @RequestBody Estabelecimento profissional) {
		try {
			return new ResponseEntity<Estabelecimento>(estabelecimentoRepository.save(profissional),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Estabelecimento> update(@PathVariable int id, @RequestBody Estabelecimento estabelecimento) {
		
		if (estabelecimento.getId() != id) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

		return estabelecimentoRepository.findById(Integer.valueOf(id)).map(mapper -> {
			mapper.setNome(estabelecimento.getNome());
			mapper.setEndereco(estabelecimento.getEndereco());
			Estabelecimento updated = estabelecimentoRepository.save(mapper);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Estabelecimento> delete(@PathVariable int id) {

		Estabelecimento estabelecimentoExiste = estabelecimentoRepository.findById(id);

		if (estabelecimentoExiste != null) {
			estabelecimentoRepository.deleteById(id);
			return new ResponseEntity<Estabelecimento>(estabelecimentoExiste, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Estabelecimento>(HttpStatus.NOT_FOUND);
	}

}
