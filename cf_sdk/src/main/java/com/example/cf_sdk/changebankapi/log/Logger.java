package com.example.cf_sdk.changebankapi.log;

/**
 *
 * Log interface.
 */

public interface Logger {
    public static final String ON_CREATE = "onCreate";
    void verbose(String tag, String message);
    void verbose(String tag, String message, Throwable e);
    void debug(String tag, String message);
    void debug(String tag, String message, Throwable e);
    void info(String tag, String message);
    void info(String tag, String message, Throwable e);
    void warning(String tag, String message);
    void warning(String tag, String message, Throwable e);
    void error(String tag, String message);
    void error(String tag, String message, Throwable e);
}
