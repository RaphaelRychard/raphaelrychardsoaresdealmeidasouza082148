package br.gov.mt.seplag.artistalbumapi.modules.regional.exception;

/**
 * Exceção lançada quando ocorre erro na sincronização com a API externa
 */
public class RegionalSyncException extends RuntimeException {
    
    public RegionalSyncException(String message) {
        super(message);
    }
    
    public RegionalSyncException(String message, Throwable cause) {
        super(message, cause);
    }
}
