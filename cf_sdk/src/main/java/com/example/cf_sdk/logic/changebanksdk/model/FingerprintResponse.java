package com.example.cf_sdk.logic.changebanksdk.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *
 * Response object for the fingerprint reader.
 */

public final class FingerprintResponse {
    @NonNull
    public final ReadState readState;

    @Nullable
    public final CharSequence message;

    @Nullable
    public final String value;

    public FingerprintResponse(@NonNull ReadState readState,
                               @Nullable CharSequence message,
                               @Nullable String value) {
        this.readState = readState;
        this.message = message;
        this.value = value;
    }

    public enum ReadState {
        BIOMETRIC_SUCCESS,
        BIOMETRIC_ERROR_NO_HARDWARE,
        BIOMETRIC_ERROR_HW_UNAVAILABLE,
        BIOMETRIC_ERROR_NONE_ENROLLED
    }
}
