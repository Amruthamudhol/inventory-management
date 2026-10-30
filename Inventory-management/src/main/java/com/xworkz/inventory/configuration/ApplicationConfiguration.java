
package com.xworkz.inventory.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.xworkz.inventory")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class ApplicationConfiguration implements WebMvcConfigurer {

    private int maxUploadSizeInMb = 5;

    public ApplicationConfiguration() {
        System.out.println("ApplicationConfiguration() created");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {

        CommonsMultipartResolver resolver =
                new CommonsMultipartResolver();

        resolver.setMaxUploadSize(10 * 1024 * 1024);

        resolver.setMaxUploadSizePerFile(5 * 1024 * 1024);

        return resolver;
    }
}