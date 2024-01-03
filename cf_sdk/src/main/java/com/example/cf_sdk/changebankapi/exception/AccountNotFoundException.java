package com.example.cf_sdk.changebankapi.exception;


import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 5/9/18.
 *
 * Exception thrown in case of an Account was not found.
 */

public class AccountNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_account_not_found;
    }
}
