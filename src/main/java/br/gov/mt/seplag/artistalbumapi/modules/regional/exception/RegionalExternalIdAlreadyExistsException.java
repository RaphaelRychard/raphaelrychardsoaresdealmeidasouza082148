package br.gov.mt.seplag.artistalbumapi.modules.regional.exception;

public class RegionalExternalIdAlreadyExistsException extends RuntimeException {
    public RegionalExternalIdAlreadyExistsException(String externalId) {
        super("Regional with externalId '" + externalId + "' already exists");
    }
}
