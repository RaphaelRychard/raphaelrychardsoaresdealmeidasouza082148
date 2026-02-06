package br.gov.mt.seplag.artistalbumapi.infra.exception;

public class AuthenticationUserException extends RuntimeException {
    public AuthenticationUserException() {
        super("Invalid credentials");
    }
}
