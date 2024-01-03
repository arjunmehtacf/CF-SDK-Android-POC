package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 5/15/18.
 *
 * Exception to use when an invalid transaction is detected.
 */

public class InvalidTransactionException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_invalid_transaction;
    }
}
