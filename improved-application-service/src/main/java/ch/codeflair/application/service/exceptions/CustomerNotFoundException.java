package ch.codeflair.application.service.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
