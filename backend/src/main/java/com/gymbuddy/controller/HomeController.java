package com.gymbuddy.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() throws IOException {
        Resource resource = new ClassPathResource("static/index.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

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

    @GetMapping(value = "/workouts", produces = MediaType.TEXT_HTML_VALUE)
    public String workouts() throws IOException {
        Resource resource = new ClassPathResource("static/workouts.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/achievements", produces = MediaType.TEXT_HTML_VALUE)
    public String achievements() throws IOException {
        Resource resource = new ClassPathResource("static/achievements.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/shop", produces = MediaType.TEXT_HTML_VALUE)
    public String shop() throws IOException {
        Resource resource = new ClassPathResource("static/shop.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/workout-animation", produces = MediaType.TEXT_HTML_VALUE)
    public String workoutAnimation() throws IOException {
        Resource resource = new ClassPathResource("static/workout-animation.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/workout-fragments/{fragmentName}", produces = MediaType.TEXT_HTML_VALUE)
    public String workoutFragment(@PathVariable String fragmentName) throws IOException {
        Resource resource = new ClassPathResource("static/workout-fragments/" + fragmentName);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    @GetMapping(value = "/train/{workoutId}", produces = MediaType.TEXT_HTML_VALUE)
    public String workoutDetail(@PathVariable String workoutId) throws IOException {
        Resource resource = new ClassPathResource("static/workout-detail.html");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}

