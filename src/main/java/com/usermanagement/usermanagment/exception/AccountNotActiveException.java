package com.usermanagement.usermanagment.exception;

public class AccountNotActiveException extends RuntimeException {
    public AccountNotActiveException(String userAccountIsNotEnabled) {
        super(userAccountIsNotEnabled);
    }
}
