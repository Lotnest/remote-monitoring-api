package dev.lotnest.remotemonitoring.api.controller;

import dev.lotnest.remotemonitoring.api.model.Application;
import dev.lotnest.remotemonitoring.api.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public Mono<ResponseEntity<Application>> createApplication(@RequestBody Application application) {
        log.info("Received request to create application: {}", application);
        return applicationService.save(application)
                .map(savedApplication -> new ResponseEntity<>(savedApplication, HttpStatus.CREATED));
    }
}
