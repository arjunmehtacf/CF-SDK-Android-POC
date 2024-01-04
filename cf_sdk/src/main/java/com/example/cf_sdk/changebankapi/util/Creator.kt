package com.example.cf_sdk.changebankapi.util

import com.example.cf_sdk.changebankapi.log.Logger


/**
 *
 * Base creator
 */
interface Creator<P, R> {
    fun create(logger: Logger?, parameter: P): R
}