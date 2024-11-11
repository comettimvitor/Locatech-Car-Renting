package br.com.locatech.locatech.services.exceptions;

public class VehicleNotAvailableException extends RuntimeException{

    public VehicleNotAvailableException(String message) {
        super(message);
    }
}