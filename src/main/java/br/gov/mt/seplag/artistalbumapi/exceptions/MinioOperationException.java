package br.gov.mt.seplag.artistalbumapi.exceptions;

public class MinioOperationException extends RuntimeException {
    public MinioOperationException() {
        super("Failed to process file in storage service");
    }
}
