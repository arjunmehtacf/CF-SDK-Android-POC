package com.example.cf_sdk.logic.sdk_module.singleton;

import android.app.Application;

import com.example.cf_sdk.logic.changebanksdk.log.Logger;
import com.example.cf_sdk.logic.changebanksdk.model.member.ChangebankLogLevel;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.LogParameters;
import com.example.cf_sdk.logic.changebanksdk.response.ChangebankResponse;
import com.example.cf_sdk.logic.changebanksdk.task.member.LoggerTask;
import com.google.common.base.Throwables;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 *
 * Changebank Logger
 */

public class ChangebankLogger implements Logger {

    private final LoggerTask mLoggerTask;
    private final String mAppVersion;

    @Inject
    public ChangebankLogger(LoggerTask loggerTask, Application context) {
        this.mLoggerTask = loggerTask;
        this.mAppVersion = "V1.0.12UNIT";
    }


    @Override
    public void verbose(String tag, String message) {

    }

    @Override
    public void verbose(String tag, String message, Throwable e) {

    }

    @Override
    public void debug(String tag, String message) {
        debug(tag, message, null);
    }

    @Override
    public void debug(String tag, String message, Throwable e) {
        executeLoggerTask(ChangebankLogLevel.DEBUG, tag, message, e);
    }

    @Override
    public void info(String tag, String message) {

    }

    @Override
    public void info(String tag, String message, Throwable e) {

    }

    @Override
    public void warning(String tag, String message) {

    }

    @Override
    public void warning(String tag, String message, Throwable e) {

    }

    @Override
    public void error(String tag, String message) {
        error(tag, message, null);
    }

    @Override
    public void error(String tag, String message, Throwable e) {
        executeLoggerTask(ChangebankLogLevel.ERROR, tag, message, e);
    }

    private void executeLoggerTask(ChangebankLogLevel changebankLogLevel,
                                   String tag,
                                   String message,
                                   Throwable e) {
        String stackTrace = null;
        if (e != null) {
            stackTrace = Throwables.getStackTraceAsString(e);
        }

        LogParameters logParameters = LogParameters.Create(
                changebankLogLevel,
                message,
                DeviceUtil.getOsVersion(),
                stackTrace,
                tag, mAppVersion);

        mLoggerTask.execute(new SingleObserver<ChangebankResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ChangebankResponse changebankResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }
        }, logParameters);
    }
}
