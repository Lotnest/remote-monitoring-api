package dev.lotnest.remotemonitoring.api.repository;

import dev.lotnest.remotemonitoring.api.model.Application;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ApplicationRepository extends ReactiveMongoRepository<Application, String> {
    Flux<Application> findAllByMonitoringActiveIsTrue();

    Mono<Long> countByMonitoringActiveIsFalse();
}