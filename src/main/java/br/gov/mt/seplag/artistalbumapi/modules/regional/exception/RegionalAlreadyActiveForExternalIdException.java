package br.gov.mt.seplag.artistalbumapi.modules.regional.exception;

public class RegionalAlreadyActiveForExternalIdException extends RuntimeException {
    public RegionalAlreadyActiveForExternalIdException(Integer externalId) {
        super("There is already an active regional for externalId=" + externalId);
    }
}