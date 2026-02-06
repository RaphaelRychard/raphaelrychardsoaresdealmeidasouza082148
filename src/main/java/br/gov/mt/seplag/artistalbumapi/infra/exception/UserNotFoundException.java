package br.gov.mt.seplag.artistalbumapi.infra.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found.");
    }
}
