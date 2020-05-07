package br.home.msc.profissionais_api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.home.msc.profissionais_api.models.ProfissionalRequest;
import br.home.msc.profissionais_api.repository.IEstabelecimentoRepository;
import br.home.msc.profissionais_api.repository.IProfissionalRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Profissionais", tags = "Profissional")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/profissional")
public class ProfissionalResource {

	@Autowired
	IProfissionalRepository profissionalRepository;

	@Autowired
	IEstabelecimentoRepository estabelecimentoRepository;

	@GetMapping()
	@ApiOperation(value = "Retornar uma lista de Profissionais")
	public ResponseEntity<List<Profissional>> listAll() {
		try {

			List<Profissional> profissional_retorno = profissionalRepository.findAll();
			return new ResponseEntity<List<Profissional>>(profissional_retorno, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Profissional>>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retornar um Profissional")
	public ResponseEntity<Profissional> listOne(@PathVariable(value = "id") int id) {
		try {

			Profissional profissional_retorno = profissionalRepository.findById(id);

			if (profissional_retorno != null) {
				return new ResponseEntity<Profissional>(profissional_retorno, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Profissional>(HttpStatus.NOT_FOUND);

	}

	@PostMapping()
	@ApiOperation(value = "Criar um novo Profissional")
	public ResponseEntity<Profissional> create(@Valid @RequestBody ProfissionalRequest profissional) {
		try {

			Profissional profissionalParaSave = new Profissional(profissional.getNome(), profissional.getEndereco(),
					null);

			if (profissional.getEstabelecimento_id() != null) {
				Estabelecimento estabelecimento = estabelecimentoRepository
						.findById((int) profissional.getEstabelecimento_id());

				profissionalParaSave.setEstabelecimento_id(estabelecimento);

			}

			return new ResponseEntity<Profissional>(profissionalRepository.save(profissionalParaSave),
					HttpStatus.CREATED);

		} catch (Exception e) {
			// String em = e.getMessage();
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Editar um Profissional")
	public ResponseEntity<Profissional> update(@PathVariable int id,
			@Valid @RequestBody ProfissionalRequest profissional) {

		try {
			if (profissional.getId() != id) {
				throw new Exception();
			}

			Profissional profissionaltoUpdate = new Profissional(profissional.getNome(), profissional.getEndereco(),
					null);

			Estabelecimento estabelecimento = new Estabelecimento();

			if (profissional.getEstabelecimento_id() != null) {
				Estabelecimento _estabelecimento = estabelecimentoRepository
						.findById((int) profissional.getEstabelecimento_id());

				estabelecimento.setId(_estabelecimento.getId());

			}

			return profissionalRepository.findById(Integer.valueOf(id)).map(mapper -> {
				mapper.setNome(profissionaltoUpdate.getNome());
				mapper.setEndereco(profissionaltoUpdate.getEndereco());
				mapper.setEstabelecimento_id(estabelecimento);
				Profissional updated = profissionalRepository.save(mapper);
				return ResponseEntity.ok().body(updated);
			}).orElse(ResponseEntity.notFound().build());
		} catch (Exception e) {
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Excluir um Profissional")
	public ResponseEntity<Profissional> delete(@PathVariable int id) {

		try {
			Profissional profissionalExiste = profissionalRepository.findById(id);

			if (profissionalExiste != null) {
				profissionalRepository.deleteById(id);
				return new ResponseEntity<Profissional>(profissionalExiste, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<Profissional>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Profissional>(HttpStatus.NOT_FOUND);
	}

}
