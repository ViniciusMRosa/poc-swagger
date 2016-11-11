package com.mobicare.poc;

import com.mobicare.poc.filter.MobicareSwaggerFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;
import java.util.List;


@SpringBootApplication
@EnableSwagger2
public class SwaggerApplication extends WebMvcConfigurerAdapter{

	private List<Parameter> parameterList;

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/v2/api-docs")
				.allowedOrigins("*")
				.allowedMethods("GET")
				.allowCredentials(false).maxAge(3600);
	}







	@Bean
	public FilterRegistrationBean getFilterRegistration(){

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(mobicareSwaggerFilter());

		registration.addUrlPatterns("/swagger-resources/*");
		registration.setName("mobicareSwaggerFilter");
		registration.setOrder(1);
		return registration;
	}


	@Bean
	public Filter mobicareSwaggerFilter() {
		return new MobicareSwaggerFilter();
	}


}
