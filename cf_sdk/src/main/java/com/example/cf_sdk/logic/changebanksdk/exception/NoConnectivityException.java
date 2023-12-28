package com.example.cf_sdk.logic.changebanksdk.exception;

import java.io.IOException;

/**
 *
 * Exception that is thrown when no network is detected.
 */

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No network connection!";
    }

}