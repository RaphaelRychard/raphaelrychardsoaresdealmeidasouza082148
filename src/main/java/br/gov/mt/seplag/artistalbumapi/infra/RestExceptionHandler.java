package br.gov.mt.seplag.artistalbumapi.infra;

import br.gov.mt.seplag.artistalbumapi.exceptions.InvalidReleaseYearException;
import br.gov.mt.seplag.artistalbumapi.exceptions.RegionalExternalIdAlreadyExistsException;
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
        String message = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Validation error");

        RestErrorMessage threatResponse = new RestErrorMessage(HttpStatus.BAD_REQUEST, message);
        return new ResponseEntity<>(threatResponse, HttpStatus.BAD_REQUEST);
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
}
