package dev.lotnest.remotemonitoring.api.model;

import dev.lotnest.remotemonitoring.api.enums.ApplicationEnvironment;
import dev.lotnest.remotemonitoring.api.enums.ApplicationStatus;
import dev.lotnest.remotemonitoring.api.enums.ApplicationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "applications")
@Data
@Builder
public class Application {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("url")
    private String url;

    @Field("response_time_threshold_ms")
    private int responseTimeThresholdMs;

    @Field("monitoring_active")
    private boolean monitoringActive;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("deleted_at")
    private LocalDateTime deletedAt;

    @Field("last_checked_at")
    private LocalDateTime lastCheckedAt;

    @Field("type")
    private ApplicationType type;

    @Field("environment")
    private ApplicationEnvironment environment;

    @Field("status")
    private ApplicationStatus status;

    @Field("failure_count")
    private int failureCount;

    @Field("owner_email")
    private String ownerEmail;

    @Field("tags")
    private List<String> tags;
}