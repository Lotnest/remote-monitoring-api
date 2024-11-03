package dev.lotnest.remotemonitoring.api.service;

import dev.lotnest.remotemonitoring.api.model.Application;
import dev.lotnest.remotemonitoring.api.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Mono<Application> save(Application application) {
        application.setCreatedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());
        return applicationRepository.save(application);
    }
}
