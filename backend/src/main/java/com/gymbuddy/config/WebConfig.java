package com.gymbuddy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static resources from /static directory
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        
        // Serve public assets from /public directory
        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/public/");
        
        // Explicitly handle font files with correct MIME types
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/")
                .setCachePeriod(31536000); // Cache for 1 year
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Ensure correct MIME types for font files
        configurer.mediaType("woff2", MediaType.parseMediaType("font/woff2"));
        configurer.mediaType("woff", MediaType.parseMediaType("font/woff"));
    }
}

