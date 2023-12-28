package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Exception to be thrown when Phone input does not meet the requirements.
 */

public class PhoneNotValidException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.phone_not_valid;
    }
}
