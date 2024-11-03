package dev.lotnest.remotemonitoring.api.repository;

import dev.lotnest.remotemonitoring.api.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
}