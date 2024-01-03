package com.example.cf_sdk.changebankapi.model

/**
 *
 * Response object for the fingerprint reader.
 */
class FingerprintResponse(
    val readState: ReadState,
    val message: CharSequence?,
    value: String?
) {
    var value: String? = ""

    init {
        this.value = value
    }

    enum class ReadState {
        BIOMETRIC_SUCCESS, BIOMETRIC_ERROR_NO_HARDWARE, BIOMETRIC_ERROR_HW_UNAVAILABLE, BIOMETRIC_ERROR_NONE_ENROLLED
    }
}