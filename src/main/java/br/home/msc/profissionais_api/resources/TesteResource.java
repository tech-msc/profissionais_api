package br.home.msc.profissionais_api.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Profissionais", tags = "Teste")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/teste")
public class TesteResource {

	@GetMapping()
	@ApiOperation(value = "Retorna uma mensagem se a API estiver funcionando")
	@ApiModelProperty(name = "teste")
	public String listAll() {
		return "Api funcionando";
	}

}