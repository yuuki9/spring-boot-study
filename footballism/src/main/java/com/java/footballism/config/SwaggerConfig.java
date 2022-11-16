package com.java.footballism.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //컨피규레이션 어노테이션에 대해서 공부 할 것
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	/**
	 *	swagger document
	 * 
	 * @param ViewControllerRegistry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
	    registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
	    //registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
	    registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
	}

	/**
	 *	swagger mapping
	 * 
	 * @param ResourceHandlerRegistry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		
	}
	
	
	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.java.footballism"))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.useDefaultResponseMessages(false); // �⺻���� ���õǴ� 0,401,403,404 �޽����� ǥ�� ���� ����
			
	}

	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder().title("footballism REST API Documentation").description("REST API")
				.license("").licenseUrl("").version("1").build();
	}
	
}
        
