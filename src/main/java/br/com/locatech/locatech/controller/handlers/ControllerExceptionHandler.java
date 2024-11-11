package br.com.locatech.locatech.controller.handlers;

import br.com.locatech.locatech.dto.*;
import br.com.locatech.locatech.services.exceptions.DateOutOfBoundsException;
import br.com.locatech.locatech.services.exceptions.ResourceNotFoundException;
import br.com.locatech.locatech.services.exceptions.VehicleNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodNotValidException(MethodArgumentNotValidException exception) {
        var status = HttpStatus.BAD_REQUEST;

        List<String> errors = new ArrayList<>();

        for(var error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }

    @ExceptionHandler(VehicleNotAvailableException.class)
    public ResponseEntity<VehicleAvailableDTO> handleVehicleNotAvailable(VehicleNotAvailableException ex) {
        var status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status.value()).body(new VehicleAvailableDTO(ex.getMessage(), status.value()));
    }

    @ExceptionHandler(DateOutOfBoundsException.class)
    public ResponseEntity<DateOutOfBoundsDTO> handleDateOutOfBounds(DateOutOfBoundsException ex) {
        var status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status.value()).body(new DateOutOfBoundsDTO(ex.getMessage(), status.value()));
    }
}
