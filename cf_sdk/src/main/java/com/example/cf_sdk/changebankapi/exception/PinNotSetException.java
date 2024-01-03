package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;
/**
 * Created by victorojeda on 11/29/16.
 *
 * Thrown when Pin login has not been set by the user.
 */

public class PinNotSetException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.pin_not_set;
    }
}
