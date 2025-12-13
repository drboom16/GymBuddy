package com.gymbuddy.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class HomeController {

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String home() throws IOException {
        Resource resource = new ClassPathResource("static/home.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/train", produces = MediaType.TEXT_HTML_VALUE)
    public String train() throws IOException {
        Resource resource = new ClassPathResource("static/train.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}

