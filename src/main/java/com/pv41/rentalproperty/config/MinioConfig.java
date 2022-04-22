package com.pv41.rentalproperty.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class MinioConfig {

    @Value("${minio.url}")
    private String url;
    @Value("${minio.bucket}")
    private String bucket;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioProvider() {
        MinioClient minioClient = MinioClient
                .builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
        checkBucket(minioClient);

        return minioClient;
    }

    private void checkBucket(MinioClient minioClient) {
        try {
            if (!(minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build()))) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
