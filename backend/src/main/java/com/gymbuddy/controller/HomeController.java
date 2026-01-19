package com.gymbuddy.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

@RestController
public class HomeController {

    private static final Path FRONTEND_STATIC_DIR = Paths.get("..", "frontend", "static");

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() throws IOException {
        return readHtml("index.html");
    }

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String home() throws IOException {
        return readHtml("home.html");
    }

    @GetMapping(value = "/train", produces = MediaType.TEXT_HTML_VALUE)
    public String train() throws IOException {
        return readHtml("train.html");
    }

    @GetMapping(value = "/workouts", produces = MediaType.TEXT_HTML_VALUE)
    public String workouts() throws IOException {
        return readHtml("workouts.html");
    }

    @GetMapping(value = "/achievements", produces = MediaType.TEXT_HTML_VALUE)
    public String achievements() throws IOException {
        return readHtml("achievements.html");
    }

    @GetMapping(value = "/shop", produces = MediaType.TEXT_HTML_VALUE)
    public String shop() throws IOException {
        return readHtml("shop.html");
    }

    @GetMapping(value = "/collection", produces = MediaType.TEXT_HTML_VALUE)
    public String collection() throws IOException {
        return readHtml("collection.html");
    }

    @GetMapping(value = "/workout-animation", produces = MediaType.TEXT_HTML_VALUE)
    public String workoutAnimation() throws IOException {
        return readHtml("workout-animation.html");
    }

    @GetMapping(value = "/workout-fragments/{fragmentName}", produces = MediaType.TEXT_HTML_VALUE)
    public String workoutFragment(@PathVariable String fragmentName) throws IOException {
        return readHtml(Paths.get("workout-fragments", fragmentName).toString());
    }

    @GetMapping(value = "/train/{workoutId}", produces = MediaType.TEXT_HTML_VALUE)
    public String workoutDetail(@PathVariable String workoutId) throws IOException {
        return readHtml("workout-detail.html");
    }

    private String readHtml(String relativePathFromStatic) throws IOException {
        Path file = FRONTEND_STATIC_DIR.resolve(relativePathFromStatic).normalize();
        if (Files.exists(file) && Files.isRegularFile(file)) {
            return Files.readString(file, StandardCharsets.UTF_8);
        }

        // Fallback to classpath if someone builds a jar without the external frontend folder
        Resource resource = new ClassPathResource("static/" + relativePathFromStatic);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}

