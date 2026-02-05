package br.gov.mt.seplag.artistalbumapi.infra;

import br.gov.mt.seplag.artistalbumapi.exceptions.*;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.InvalidAlbumReleaseYearException;
import br.gov.mt.seplag.artistalbumapi.modules.artist.exception.ArtistNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.security.core.AuthenticationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestErrorMessage> handleConstraintViolation(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).findFirst().orElse("Validation error");

        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(threatResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserFoundException.class)
    private ResponseEntity<RestErrorMessage> userFoundHandler(UserFoundException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(AuthenticationUserException.class)
    private ResponseEntity<RestErrorMessage> authenticationUserHandler(AuthenticationUserException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(threatResponse);
    }

    @ExceptionHandler(RegionalExternalIdAlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> RegionalAlreadyExistsHandler(RegionalExternalIdAlreadyExistsException exception) {
        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(threatResponse);
    }

    @ExceptionHandler(InvalidReleaseYearException.class)
    private ResponseEntity<RestErrorMessage> invalidReleaseYearHandler(InvalidReleaseYearException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    private ResponseEntity<RestErrorMessage> albumNotFoundHandler(AlbumNotFoundException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidAlbumReleaseYearException.class)
    private ResponseEntity<RestErrorMessage> invalidAlbumReleaseYearHandler(InvalidAlbumReleaseYearException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    private ResponseEntity<RestErrorMessage> artistNotFoundHandler(ArtistNotFoundException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<RestErrorMessage> illegalArgumentHandler(IllegalArgumentException exception) {
        RestErrorMessage error = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
