package com.xworkz.inventory.configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebConfigurator implements WebMvcConfigurer {
    public WebConfigurator() {
        log.info("WebConfigurator started");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }
}
