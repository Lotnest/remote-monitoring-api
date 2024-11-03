package dev.lotnest.remotemonitoring.api.service;

import dev.lotnest.remotemonitoring.api.enums.ApplicationStatus;
import dev.lotnest.remotemonitoring.api.model.Application;
import dev.lotnest.remotemonitoring.api.repository.ApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MonitoringService {
    private final ApplicationRepository applicationRepository;
    private final WebClient webClient;

    public MonitoringService(ApplicationRepository applicationRepository, WebClient.Builder webClientBuilder) {
        this.applicationRepository = applicationRepository;
        this.webClient = webClientBuilder.build();
    }

    public Mono<Application> performHealthChecks(Application application) {
        return webClient.get()
                .uri(application.getUrl())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> ApplicationStatus.UP)
                .onErrorResume(error -> Mono.just(ApplicationStatus.DOWN))
                .doOnNext(newStatus -> updateApplicationStatus(application, newStatus))
                .flatMap(updatedApplication -> applicationRepository.save(application));
    }

    public Mono<Application> updateApplicationStatus(Application application, ApplicationStatus newStatus) {
        if (application.getStatus() != newStatus) {
            application.setStatus(newStatus);
        }

        if (application.getStatus() == ApplicationStatus.DOWN) {
            application.setFailureCount(application.getFailureCount() + 1);
        }

        application.setLastCheckedAt(LocalDateTime.now());

        return Mono.just(application);
    }
}
