package com.example.cf_sdk.changebankapi.exception;

/**
 * Created by gunveernatt on 11/10/17.
 *
 * Exception to be thrown when too many incorrect attempts for login two factor.
 */

public class LoginTwoFactorTooManyAttemptsException extends BaseException {


    private final String mMessage;

    public LoginTwoFactorTooManyAttemptsException(String message) {
        super();
        mMessage = message;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
