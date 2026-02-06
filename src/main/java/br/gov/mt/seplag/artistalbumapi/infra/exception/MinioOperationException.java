package br.gov.mt.seplag.artistalbumapi.infra.exception;

public class MinioOperationException extends RuntimeException {
    public MinioOperationException() {
        super("Failed to process file in storage service");
    }
}
