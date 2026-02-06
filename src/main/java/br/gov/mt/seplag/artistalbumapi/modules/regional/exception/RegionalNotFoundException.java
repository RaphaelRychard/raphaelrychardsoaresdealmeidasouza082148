package br.gov.mt.seplag.artistalbumapi.modules.regional.exception;

public class RegionalNotFoundException extends RuntimeException {
    
    public RegionalNotFoundException(String id) {
        super("Regional not found with ID: " + id);
    }
}
