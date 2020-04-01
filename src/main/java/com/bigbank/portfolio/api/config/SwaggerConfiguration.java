package com.bigbank.portfolio.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {


        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.bigbank.nyse.test"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());
        }


    private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Basic Portfolio")
                    .description("Managing Basic portfolio")
                    .termsOfServiceUrl("https://github.com/marco911ie/portfolio-wipro")
                    .version("1.0")
                    .build();
        }
    }

