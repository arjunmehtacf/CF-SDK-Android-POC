package com.example.cf_sdk.changebankapi.log;

import android.util.Log;

/**
 * Created by victorojeda on 10/26/17.
 * <p>
 * Android Logger.
 */

public class AndroidLogger implements Logger {
    @Override
    public void verbose(String tag, String message) {
        Log.v(tag, message);
    }

    @Override
    public void verbose(String tag, String message, Throwable throwable) {
        Log.v(tag, message, throwable);
    }

    @Override
    public void debug(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void debug(String tag, String message, Throwable throwable) {
        Log.d(tag, message, throwable);
    }

    @Override
    public void info(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void info(String tag, String message, Throwable throwable) {
        Log.i(tag, message, throwable);
    }

    @Override
    public void warning(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void warning(String tag, String message, Throwable throwable) {
        Log.w(tag, message, throwable);
    }

    @Override
    public void error(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }

}
