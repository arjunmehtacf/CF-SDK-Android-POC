package com.example.cf_sdk.changebankapi.log;



/**
 *
 */

public class LogManager implements Logger {
    private Logger mChangebankLogger;
    private Logger mLogger;
    private static boolean mShouldEnableDebugChangebankLog = false;

    public LogManager() {
        setDefaultLogger();
    }

    public Logger getLogger() {
        return this.mLogger;
    }

    @Override
    public void verbose(String tag, String message) {
        this.mLogger.verbose(tag, message);
    }

    @Override
    public void verbose(String tag, String message, Throwable e) {
        this.mLogger.verbose(tag, message, e);
    }

    @Override
    public void debug(String tag, String message) {
        this.mLogger.debug(tag, message);
        if (mShouldEnableDebugChangebankLog && isChangebankLoggerNotNull()) {
            this.mChangebankLogger.debug(tag, message);
        }
    }

    @Override
    public void debug(String tag, String message, Throwable e) {
        this.mLogger.debug(tag, message, e);
        if (mShouldEnableDebugChangebankLog && isChangebankLoggerNotNull()) {
            this.mChangebankLogger.debug(tag, message, e);
        }
    }

    @Override
    public void info(String tag, String message) {
        this.mLogger.info(tag, message);

    }

    @Override
    public void info(String tag, String message, Throwable e) {
        this.mLogger.info(tag, message, e);
    }

    @Override
    public void warning(String tag, String message) {
        this.mLogger.warning(tag, message);
    }

    @Override
    public void warning(String tag, String message, Throwable e) {
        this.mLogger.warning(tag, message, e);
    }

    @Override
    public void error(String tag, String message) {
        this.mLogger.error(tag, message);
        if (isChangebankLoggerNotNull()) {
            this.mChangebankLogger.error(tag, message);
        }
    }

    @Override
    public void error(String tag, String message, Throwable e) {
        this.mLogger.error(tag, message, e);
        if (isChangebankLoggerNotNull()) {
            this.mChangebankLogger.error(tag, message, e);
        }

    }

    private boolean isChangebankLoggerNotNull() {
        return mChangebankLogger != null;
    }


    private void setDefaultLogger() {
//        if (BuildConfig.DEBUG) {
            this.mLogger = new AndroidLogger();
//        } else {
//            this.mLogger = new EmptyLogger();
//        }
    }

    public void enableDebugChangebankLog(boolean shouldEnable) {
        mShouldEnableDebugChangebankLog = shouldEnable;
    }
}
