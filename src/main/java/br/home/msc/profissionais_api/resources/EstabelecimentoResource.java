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
import br.home.msc.profissionais_api.repository.IEstabelecimentoRepository;
import br.home.msc.profissionais_api.repository.IProfissionalRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Profissionais", tags = "Estabelecimento")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "/api/estabelecimento" })
public class EstabelecimentoResource {

	@Autowired
	IProfissionalRepository profissionalRepository;

	@Autowired
	IEstabelecimentoRepository estabelecimentoRepository;

	@GetMapping
	@ApiOperation(value = "Retornar uma lista de Estabelecimentos")
	public ResponseEntity<List<Estabelecimento>> listAll() {
		try {

			List<Estabelecimento> estabelecimento_retorno = estabelecimentoRepository.findAll();
			return new ResponseEntity<List<Estabelecimento>>(estabelecimento_retorno, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Estabelecimento>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retornar um Estabelecimento")
	public ResponseEntity<Estabelecimento> listOne(@PathVariable(value = "id") int id) {
		try {

			Estabelecimento estabelecimento_retorno = estabelecimentoRepository.findById(id);

			if (estabelecimento_retorno != null) {
				return new ResponseEntity<Estabelecimento>(estabelecimento_retorno, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Estabelecimento>(HttpStatus.NOT_FOUND);

	}

	@PostMapping
	@ApiOperation(value = "Criar um novo Estabelecimento")
	public ResponseEntity<Estabelecimento> create(@Valid @RequestBody Estabelecimento profissional) {
		try {
			return new ResponseEntity<Estabelecimento>(estabelecimentoRepository.save(profissional),
					HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Editar um Estabelecimento")
	public ResponseEntity<Estabelecimento> update(@PathVariable int id, @RequestBody Estabelecimento estabelecimento) {

		try {
			if (estabelecimento.getId() != id) {
				throw new Exception();
			}

			return estabelecimentoRepository.findById(Integer.valueOf(id)).map(mapper -> {
				mapper.setNome(estabelecimento.getNome());
				mapper.setEndereco(estabelecimento.getEndereco());
				Estabelecimento updated = estabelecimentoRepository.save(mapper);
				return ResponseEntity.ok().body(updated);
			}).orElse(ResponseEntity.notFound().build());

		} catch (Exception e) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Excluir um Estabelecimento")
	public ResponseEntity<Estabelecimento> delete(@PathVariable int id) {

		try {
			Estabelecimento estabelecimentoExiste = estabelecimentoRepository.findById(id);

			if (estabelecimentoExiste != null) {
				estabelecimentoRepository.deleteById(id);
				return new ResponseEntity<Estabelecimento>(estabelecimentoExiste, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Estabelecimento>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Estabelecimento>(HttpStatus.NOT_FOUND);
	}

}
