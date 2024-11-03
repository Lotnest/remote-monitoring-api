package dev.lotnest.remotemonitoring.api.scheduler;

import dev.lotnest.remotemonitoring.api.repository.ApplicationRepository;
import dev.lotnest.remotemonitoring.api.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MonitoringScheduler {

    private final ApplicationRepository applicationRepository;
    private final MonitoringService monitoringService;

    @Scheduled(fixedRate = 60000)
    public void performHealthChecks() {
        log.info("Performing health checks for all active applications");
        applicationRepository.findAllByMonitoringActiveIsTrue()
                .flatMap(monitoringService::performHealthChecks)
                .subscribe(application -> log.info("{}: {}", application.getId(), application.getStatus()));

        applicationRepository.countByMonitoringActiveIsFalse()
                .subscribe(count -> log.info("Ignoring health checks for {} inactive applications", count));
    }
}
