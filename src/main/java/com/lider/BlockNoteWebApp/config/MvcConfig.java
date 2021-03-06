package com.lider.BlockNoteWebApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}") // Using Spring Expression language. Searching for upload path in properties
    private String uploadPath; //

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + uploadPath + "/"); //все пути по запросу /img/** юудут перенаправляться по пути uploadPath
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/"); //classpath означат, что при обращнии по пути ресурсы будут искаться в дереве проекта от корня
    }
}