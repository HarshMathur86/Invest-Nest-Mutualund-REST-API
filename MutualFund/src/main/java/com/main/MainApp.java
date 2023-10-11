package com.main;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MainApp {
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
		System.out.println("Invest Nest Spring Boot app started...");
	}
	
	
	// The primary purpose of this @Bean annotation is to enable the spring
	// container to call this method automatically when you start the application
	// You don't need to call this method EXPLICITLY
	@Bean
	public Docket swaggerConfiguration()
	{
		ParameterBuilder aParameterBuilder=new ParameterBuilder();
		
		aParameterBuilder.name("Authorization")
		.modelRef(new ModelRef("string"))
		.parameterType("header")
		.defaultValue("Basic lijrowqjelfkj")
		.required(false)
		.build();
		
		java.util.List<Parameter> allParameters=new ArrayList<>();
		
		allParameters.add(aParameterBuilder.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**")) // this is for all URIs & sub-URIs
				//.paths(PathSelectors.ant("/*")) // this is only for all URIs
				//.paths(PathSelectors("/api/*")) ;; this is for all URIs starting with api
				.apis(RequestHandlerSelectors.basePackage("com.main"))
				.build()
				.globalOperationParameters(allParameters)
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
	    return new ApiInfo(
	        "Invest Nest REST API",
	        "Mutual Fund Investment",
	        "1.0",
	        "Free APIs",
	        null, // Remove the Contact parameter
	        null,
	        "", // Set the empty string for the URL
	        Collections.emptyList()
	    );
	}	
	
}
