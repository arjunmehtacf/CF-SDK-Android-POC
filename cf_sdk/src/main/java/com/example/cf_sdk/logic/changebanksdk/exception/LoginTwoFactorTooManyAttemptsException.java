package com.example.cf_sdk.logic.changebanksdk.exception;

/**
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
