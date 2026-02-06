package br.gov.mt.seplag.artistalbumapi.infra.health;

import io.minio.MinioClient;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("minio")
public class MinioHealthIndicator implements HealthIndicator {

    private final MinioClient minioClient;

    public MinioHealthIndicator(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public Health health() {
        try {
            minioClient.listBuckets();
            return Health.up().build();
        } catch (Exception ex) {
            return Health.down()
                    .withDetail("reason", "Failed to reach MinIO")
                    .build();
        }
    }
}