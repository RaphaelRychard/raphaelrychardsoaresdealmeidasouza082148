package br.gov.mt.seplag.artistalbumapi.exceptions;

import java.time.Year;

public class InvalidReleaseYearException extends RuntimeException {
    public InvalidReleaseYearException() {
        super("Release year must be between 1900 and " + Year.now().getValue());
    }
}
