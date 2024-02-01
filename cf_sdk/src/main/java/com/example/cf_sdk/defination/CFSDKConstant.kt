package com.example.cf_sdk.defination

object CFSDKConstant {
    const val KEY_BASE_URL = "key_base_url"
    const val X_APPLICATION_ID = "X-Application-ID"
    const val TOEKN_TYPE = "Bearer"
    const val APP_ID = "f4665ee1-f8c1-4111-baa5-e755a2e83320"
    const val ACCESS_TOKEN_FOR_EX_USER_AUTHORIZATION = "Bearer xL5I9DCkb5jqd9o5iJ2a2MVSkm+OP7IwtrVABfElC9dphvQLWPgkuQEdSRhi0dU0"
    const val INSTANCE_NAME_KEY = "InstanceName"
    const val INSTANCE_NAME_VALUE = "e49"


    fun generateRandomString(length: Int): String {
        val charset = "12345678901234567890123456789012"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }
}