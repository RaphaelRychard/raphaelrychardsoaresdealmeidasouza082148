package br.gov.mt.seplag.artistalbumapi.infra.exception;

import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumWithoutArtistException;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.InvalidAlbumReleaseYearException;
import br.gov.mt.seplag.artistalbumapi.modules.artist.exception.ArtistNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalAlreadyActiveForExternalIdException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalExternalIdAlreadyExistsException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalSyncException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestErrorMessage> handleConstraintViolation(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).findFirst().orElse("Validation error");

        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UserFoundException.class)
    private ResponseEntity<RestErrorMessage> userFoundHandler(UserFoundException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationUserException.class)
    private ResponseEntity<RestErrorMessage> authenticationUserHandler(AuthenticationUserException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
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

    @ExceptionHandler(RegionalNotFoundException.class)
    private ResponseEntity<RestErrorMessage> handleRegionalNotFound(RegionalNotFoundException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(RegionalSyncException.class)
    private ResponseEntity<RestErrorMessage> handleRegionalSync(RegionalSyncException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_GATEWAY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }

    @ExceptionHandler(MinioOperationException.class)
    private ResponseEntity<RestErrorMessage> handleMinioOperation(MinioOperationException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_GATEWAY, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorResponse);
    }

    @ExceptionHandler({RegionalAlreadyActiveForExternalIdException.class, RegionalExternalIdAlreadyExistsException.class})
    private ResponseEntity<RestErrorMessage> handleRegionalConflict(RuntimeException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<RestErrorMessage> illegalArgumentHandler(IllegalArgumentException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AlbumWithoutArtistException.class)
    public ResponseEntity<RestErrorMessage> albumWithoutArtistHandler(AlbumWithoutArtistException exception) {
        RestErrorMessage errorResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}