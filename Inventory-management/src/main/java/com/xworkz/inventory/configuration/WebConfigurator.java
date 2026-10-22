package com.xworkz.inventory.configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfigurator implements WebMvcConfigurer {
    public WebConfigurator() {
        System.out.println("WebConfigurator started");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }
}
