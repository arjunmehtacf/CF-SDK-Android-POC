package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.model.member.ChangebankLogLevel;
import com.example.cf_sdk.logic.changebanksdk.model.member.ClientType;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Handles parameters for log request.
 */

public class LogParameters extends Parameters {

    @SerializedName("level")
    private ChangebankLogLevel mLogLevel;

    @SerializedName("message")
    private String mLogMessage;

    @SerializedName("memberId")
    private long mMemberId;

    @SerializedName("client")
    private ClientType mClientType;

    @SerializedName("os")
    private String mOsVersion;

    @SerializedName("stackTrace")
    private String mStackTrace;

    @SerializedName("tag")
    private String mLogTag;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("sessionId")
    private String mSessionId;

    public static LogParameters Create(ChangebankLogLevel logLevel,
                                       String logMessage,
                                       String osVersion,
                                       String stackTrace,
                                       String logTag,
                                       String version) {
        return new LogParameters(
                new HashMap<String, String>(),
                logLevel,
                logMessage,
                -1,
                ClientType.ANDROID,
                osVersion,
                stackTrace,
                logTag,
                version,
                null);
    }

    private LogParameters(Map<String, String> headers,
                          ChangebankLogLevel logLevel,
                          String logMessage,
                          long memberId,
                          ClientType clientType,
                          String osVersion,
                          String stackTrace,
                          String logTag,
                          String version,
                          String sessionId) {
        super(headers);
        mLogLevel = logLevel;
        mLogMessage = logMessage;
        mMemberId = memberId;
        mClientType = clientType;
        mOsVersion = osVersion;
        mStackTrace = stackTrace;
        mLogTag = logTag;
        mVersion = version;
        mSessionId = sessionId;
    }


    public LogParameters withMemberId(long memberId) {
        return new LogParameters(
                getHeaders(),
                mLogLevel,
                mLogMessage,
                memberId,
                mClientType,
                mOsVersion,
                mStackTrace,
                mLogTag,
                mVersion,
                mSessionId);
    }


    public LogParameters withSessionId(String token) {
        return new LogParameters(
                getHeaders(),
                mLogLevel,
                mLogMessage,
                mMemberId,
                mClientType,
                mOsVersion,
                mStackTrace,
                mLogTag,
                mVersion,
                token);
    }

}
