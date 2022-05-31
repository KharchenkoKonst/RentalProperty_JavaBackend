package com.pv41.rentalproperty.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;
    @Value("${minio.bucket}")
    private String bucket;

    public MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void saveToStorage(byte[] data, String url) {
        try {
            InputStream inputStream = new ByteArrayInputStream(data);
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(url)
                            .stream(inputStream, data.length, -1)
                            .contentType("image/png")
                            .build()
            );
            inputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Optional<byte[]> getFromStorage(String url) {
        try {
            InputStream inputStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(url)
                            .build()
            );
            byte[] image = inputStream.readAllBytes();
            inputStream.close();
            return Optional.ofNullable(image);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public String getMinioUrl() {
        try {
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("response-content-type", "image");
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object("b0a9ccdd-eb6c-4dc8-9402-6813d150402d")
                            .expiry(2, TimeUnit.HOURS)
                            .extraQueryParams(reqParams)
                            .build()
            );
        } catch (Exception e){
            return "";
        }
    }

}