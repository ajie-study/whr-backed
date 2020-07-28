package com.whr.config;

import com.whr.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : MyWebMvcConfigurerAdapter
 * @Description : 配置拦截器和跨域
 * @Author : zhj
 * @Date: 2020-07-20 16:24
 */
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {


    @Value("${excludePath}")
    private String excludePath;


    /**
     * 添加拦截器的方式，可直接new一个对象，
     * registry.addInterceptor(new ParamInterceptor())，
     * 但通过手动new出来的拦截器中，无法使用@Autowired 和 @Value 加载对象和配置文件参数。
     * <p>
     * 所以需要在添加拦截器此处，通过@Bean 注解，意味着将这个对象
     * 交给spring管理。那么该拦截器才可以使用@Value等spring管理的注解
     *
     * @return
     */
//    @Bean
//    public TokenInterceptor paramInterceptor() {
//        return new TokenInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        //paramInterceptor()是一个参数拦截器拦截 /api/接口的参数
//        registry.addInterceptor(paramInterceptor()).addPathPatterns("/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 忽略路径
        String[] split = excludePath.split(",");

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

        interceptorRegistration.excludePathPatterns(split);

    }


    /**
     * 解决swagger-ui.html 404无法访问的问题
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}