package com.whr.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @ClassName : Swagger2
 * @Description : swagger2
 * @Author : zhj
 * @Date: 2020-07-17 15:33
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //指定要生成api文档的根包
                .apis(RequestHandlerSelectors.basePackage("com.whr.controller"))
                //路径配置
                .paths(regex("/.*"))
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WHR 的 Restful API 文档")
                .description("WHR 的 Restful API 文档")
                .version("1.0")
                .build();
    }
}