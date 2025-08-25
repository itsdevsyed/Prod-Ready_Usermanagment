package com.usermanagement.usermanagment.exception;

public class EmailAlreadyExistsException extends Throwable {
    public EmailAlreadyExistsException(String emailAlreadyExists) {
        super(emailAlreadyExists);
    }
}
