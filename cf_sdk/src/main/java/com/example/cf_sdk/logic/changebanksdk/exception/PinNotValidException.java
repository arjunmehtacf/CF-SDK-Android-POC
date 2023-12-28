package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Exception to be thrown when Pin input does not meet minimum requirements.
 */

public class PinNotValidException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.pin_not_valid;
    }
}
