package br.gov.mt.seplag.artistalbumapi.infra.exception;

import java.time.Year;

public class InvalidReleaseYearException extends RuntimeException {
    public InvalidReleaseYearException() {
        super("Release year must be between 1900 and " + Year.now().getValue());
    }
}
