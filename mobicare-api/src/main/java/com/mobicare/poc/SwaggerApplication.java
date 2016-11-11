package com.mobicare.poc;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.collect.Lists;
import com.mobicare.poc.rest.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.DocumentationContextBuilder;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


@SpringBootApplication
@EnableSwagger2
public class SwaggerApplication extends WebMvcConfigurerAdapter{

	private List<Parameter> parameterList;

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")

				.allowedOrigins("*")
				.allowedMethods("GET","POST","PUT","DELETE")
				.allowCredentials(false).maxAge(3600);
	}

	@Bean
	public Docket getDocket() {


		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("home-api")
				.select()

				/**
				 * Podemos filtrar por objetos em pacotes, métodos anotados, classes anotadas, todos ou nenhum.
				 */
				.apis(RequestHandlerSelectors.basePackage(HomeController.class.getPackage().getName()))
				.paths(PathSelectors.regex("/rest/home.*"))
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newResponseArrayList(new ResponseMessageBuilder()
										.code(500)
										.message("500 message")
										.responseModel(new ModelRef("Error"))
										.build(),
								new ResponseMessageBuilder()
										.code(403)
										.message("Forbidden!")
										.build()))

				;

	}

	private List<ResponseMessage> newResponseArrayList(ResponseMessage error, ResponseMessage build) {
		return Arrays.asList(new ResponseMessage[]{error,build});
	}


	@Bean
	public Docket getDocketOther(){


		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("users-api")
				.select()
				.apis(RequestHandlerSelectors.basePackage(HomeController.class.getPackage().getName()))
				.paths(PathSelectors.regex("/rest/user.*|/rest/contact.*"))
				.build()
				.apiInfo(apiInfo());

	}

	@Bean
	public Docket getDocketAll(){


		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("all-api")
				.select()
				.apis(RequestHandlerSelectors.basePackage(HomeController.class.getPackage().getName()))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());

	}
	@Bean
	public ApiInfo apiInfo(){


		return new ApiInfoBuilder()
				.description("API Criada como prova de conceito da utilzação de swagger para documentação de nossas APIs Rest")
				.license("Copyright Mobicare")
				.licenseUrl("http://www.mobicare.com.br/api/licence")
				.termsOfServiceUrl("http://www.mobicare.com.br/api/temos-of-service")
				.title("API Poc Swagger Mobicare")
				.version("1.0")
				.build();
	}

}
