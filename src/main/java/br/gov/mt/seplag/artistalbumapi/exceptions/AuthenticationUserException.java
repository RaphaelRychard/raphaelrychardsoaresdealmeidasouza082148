package br.gov.mt.seplag.artistalbumapi.exceptions;

public class AuthenticationUserException extends RuntimeException {
    public AuthenticationUserException() {
        super("Invalid credentials");
    }
}
