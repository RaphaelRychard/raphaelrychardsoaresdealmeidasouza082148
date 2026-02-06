package br.gov.mt.seplag.artistalbumapi.infra.exception;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário já existe");
    }
}
