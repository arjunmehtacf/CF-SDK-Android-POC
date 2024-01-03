package com.example.cf_sdk.changebankapi.parameter.authentication;

import com.example.cf_sdk.changebankapi.parameter.Parameters
import com.google.gson.annotations.SerializedName
import java.io.UnsupportedEncodingException
import java.util.Locale

/**
 *
 * Handles login information to authenticate.
 */
class LoginParameters private constructor(
    headers: Map<String, String>,
    username: String?,
    password: String?,
    pin: String?,
    installationId: String,
    biometric: String?,
    shouldSaveUsername: Boolean,
    threatMetrixSessionId: String,
    loginMethodType: LoginMethodType,
) : Parameters(headers) {
    @SerializedName("mpin")
    var pin: String? = ""

    @SerializedName("profilingSessionId")
    private val mThreatMetrixSessionId: String

    @SerializedName("username")
    var username: String? = ""

    @SerializedName("biometric")
    var biometric: String? = ""

    @SerializedName("password")
    var password: String? = ""

    @SerializedName("deviceId")
    var installationId = ""

    @SerializedName("pin")
    val hashedPin = ""

    @Transient
    var shouldSaveUsername = false

    @Transient
    val loginMethod: LoginMethodType

    init {
        this.installationId = installationId
        this.username = username?.lowercase(Locale.getDefault())
        this.password = password
        this.pin = pin
        this.biometric = biometric
        this.shouldSaveUsername = shouldSaveUsername
        mThreatMetrixSessionId = threatMetrixSessionId
        loginMethod = loginMethodType
    }

    fun generateHashedPin(salt: ByteArray?) {
//        mHashedPin = SecurityUtils.createHashedPassword(mPin.toCharArray(), salt);
    }

    val isLoginWithPin: Boolean
        get() = pin != null

    companion object {
        @Throws(UnsupportedEncodingException::class)
        fun createWithPassword(
            username: String?,
            password: String?,
            installationId: String,
            shouldSaveUsername: Boolean,
            threatMetrixSessionId: String,
        ): LoginParameters {
            return LoginParameters(
                HashMap(),
                username,
                password,
                null,
                installationId,
                null,
                shouldSaveUsername,
                threatMetrixSessionId,
                LoginMethodType.PASSWORD_LOGIN
            )
        }

        @Throws(UnsupportedEncodingException::class)
        fun createWithPin(
            pin: String?,
            installationId: String,
            threatMetrixSessionId: String,
        ): LoginParameters {
            return LoginParameters(
                HashMap(),
                null,
                null,
                pin,
                installationId,
                null,
                true,
                threatMetrixSessionId,
                LoginMethodType.PIN_LOGIN
            )
        }

        @Throws(UnsupportedEncodingException::class)
        fun createWithBiometric(
            username: String?,
            biometric: String?,
            installationId: String,
            threatMetrixSessionId: String,
        ): LoginParameters {
            return LoginParameters(
                HashMap(),
                username,
                null,
                null,
                installationId,
                biometric,
                true,
                threatMetrixSessionId,
                LoginMethodType.FINGERPRINT_LOGIN
            )
        }
    }
}