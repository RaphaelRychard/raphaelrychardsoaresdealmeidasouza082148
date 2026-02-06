package br.gov.mt.seplag.artistalbumapi.modules.regional.exception;

/**
 * Exceção lançada quando uma regional não é encontrada
 */
public class RegionalNotFoundException extends RuntimeException {
    
    public RegionalNotFoundException(String id) {
        super("Regional not found with ID: " + id);
    }
}
