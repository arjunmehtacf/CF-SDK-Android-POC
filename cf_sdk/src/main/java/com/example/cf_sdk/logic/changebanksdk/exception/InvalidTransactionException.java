package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Exception to use when an invalid transaction is detected.
 */

public class InvalidTransactionException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_invalid_transaction;
    }
}
