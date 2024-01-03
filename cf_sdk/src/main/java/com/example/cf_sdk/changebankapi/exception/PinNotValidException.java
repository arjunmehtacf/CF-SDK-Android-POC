package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 11/20/16.
 *
 * Exception to be thrown when Pin input does not meet minimum requirements.
 */

public class PinNotValidException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.pin_not_valid;
    }
}
