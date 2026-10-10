package com.example.bioproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.addPathPrefix("/api",
                HandlerTypePredicate.forAnnotation(RestController.class));
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new PageInterceptor())
                .addPathPatterns(
                        "/choose_delete_movie.html",
                        "/choose_movie_to_delete.html",
                        "/choose_movie_to_edit.html",
                        "/create_movie.html",
                        "/create_screening_form.html",
                        "/delete_movie.html",
                        "/edit_movie.html",
                        "/movie_statistics.html",
                        "/screening_dashboard.html",
                        "/movie_settings.html"
                );
    }
}
