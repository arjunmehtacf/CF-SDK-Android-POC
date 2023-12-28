package com.example.cf_sdk.logic.changebanksdk.parameter;

import com.example.cf_sdk.logic.changebanksdk.Header;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Base class for network requests.
 */

public class Parameters implements Serializable{

    public transient static final Parameters EMPTY = new Parameters(new HashMap<String, String>());

    private transient final Map<String, String> mHeaders;

    private transient boolean mShouldClearCache = false;
    private transient boolean fromRemoteOnly = false;


    public static Parameters getEmptyParameters(boolean shouldClearCache){
        Parameters newParameters = new Parameters(new HashMap<String, String>());
        newParameters.setShouldClearCache(shouldClearCache);
        return newParameters;
    }

    public void setShouldClearCache(boolean value) {
        mShouldClearCache = value;
    }

    public boolean getShouldClearCache() {
        return mShouldClearCache;
    }

    public Parameters(Map<String, String> headers) {
        mHeaders = headers;
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public void addHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    public void addToken(String token) {
        addHeader(Header.TOKEN, token);
    }

    public boolean isFromRemoteOnly() {
        return fromRemoteOnly;
    }

    public void setFromRemoteOnly(boolean fromRemoteOnly) {
        this.fromRemoteOnly = fromRemoteOnly;
    }
}
