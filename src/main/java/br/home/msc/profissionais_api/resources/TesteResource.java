package br.home.msc.profissionais_api.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class TesteResource {

	@GetMapping()
	public String listAll() {
		return "Api funcionando";
	}

}