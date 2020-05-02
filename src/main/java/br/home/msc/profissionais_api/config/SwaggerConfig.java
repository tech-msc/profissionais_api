package br.home.msc.profissionais_api.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket profissionalApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.home.msc.profissionais_api"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"API Profissionais",
				"Fornece dados para gerenciamento de profissionais e estabelecimentos",
				"1.0",
				"Terms of Service",
				new Contact("Matheus S. Cardoso", 
						"https://www.linkedin.com/in/matheusscardoso/", 
						"matheusscardoso5@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licensen.html",
				new ArrayList<VendorExtension>());
		
		return apiInfo;
	}
	
	
}
