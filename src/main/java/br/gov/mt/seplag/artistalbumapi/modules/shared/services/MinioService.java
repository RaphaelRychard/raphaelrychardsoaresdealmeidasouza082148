package br.gov.mt.seplag.artistalbumapi.modules.shared.services;

import br.gov.mt.seplag.artistalbumapi.exceptions.MinioOperationException;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.presigned-url-expiration}")
    private int presignedUrlExpiration;

    public String uploadFile(MultipartFile file, String folder) {
        try (InputStream inputStream = file.getInputStream()) {

            createBucketIfNotExists();

            String extension = extractExtension(file);
            String objectKey = folder + "/" + UUID.randomUUID() + extension;

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectKey)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return objectKey;

        } catch (Exception e) {
            log.error("Erro no upload para MinIO", e);
            throw new MinioOperationException();
        }
    }

    public String getPresignedUrl(String objectKey) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectKey)
                            .expiry(presignedUrlExpiration, TimeUnit.SECONDS)
                            .build()
            );
        } catch (Exception e) {
            log.error("Erro ao gerar presigned URL", e);
            throw new MinioOperationException();
        }
    }

    public void deleteFile(String objectKey) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectKey)
                            .build()
            );
        } catch (Exception e) {
            log.error("Erro ao remover arquivo do MinIO", e);
            throw new MinioOperationException();
        }
    }

    private void createBucketIfNotExists() throws Exception {
        boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build()
        );

        if (!exists) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
        }
    }

    private String extractExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();

        if (filename == null) throw new MinioOperationException();

        int dot = filename.lastIndexOf('.');
        if (dot <= 0 || dot == filename.length() - 1) throw new MinioOperationException();

        return filename.substring(dot);
    }
}
