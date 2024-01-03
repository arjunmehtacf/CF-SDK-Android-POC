package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 11/28/16.
 *
 * Exception to be thrown when Phone input does not meet the requirements.
 */

public class PhoneNotValidException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.phone_not_valid;
    }
}
