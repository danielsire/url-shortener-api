package com.shortener.urlShortener;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(
						Predicates
						.not(RequestHandlerSelectors
								.basePackage("org.springframework.boot"))).build()
				.apiInfo(getApiInfo());
	}

	@SuppressWarnings("rawtypes")
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"URL Shortener", 
				"API", 
				"v1", 
				"",
				new Contact("Daniel", "https://github.com/danielsire", null), 
				"", 
				"", new ArrayList<VendorExtension>() //
		);
	}

}
