package br.gov.mt.seplag.artistalbumapi.exceptions;

public class RegionalExternalIdAlreadyExistsException extends RuntimeException {
    public RegionalExternalIdAlreadyExistsException(String externalId) {
        super("Regional with externalId '" + externalId + "' already exists");
    }
}
