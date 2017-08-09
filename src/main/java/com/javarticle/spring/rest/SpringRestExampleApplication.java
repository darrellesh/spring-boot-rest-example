package com.javarticle.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
// @ComponentScan("com.javarticle.spring.rest")
public class SpringRestExampleApplication {
    
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringRestExampleApplication.class, args);
		System.out.println(( (RESTClientExample) context.getBean("restClient")).getAllEmployees());
	}
	
	@Bean
    public RestTemplate geRestTemplate() {
        return new RestTemplate();
    }

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(regex("/.*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"My Project's REST API",
				"This is a description of your API.",
				"version-1",
				"API TOS",
				"me@wherever.com",
				"API License",
				"API License URL"
		);
		return apiInfo;
	}

}
