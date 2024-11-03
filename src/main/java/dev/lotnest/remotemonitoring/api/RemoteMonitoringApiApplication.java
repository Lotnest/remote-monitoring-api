package dev.lotnest.remotemonitoring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoAuditing
@EnableScheduling
@EnableReactiveMongoRepositories
public class RemoteMonitoringApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RemoteMonitoringApiApplication.class, args);
    }
}
