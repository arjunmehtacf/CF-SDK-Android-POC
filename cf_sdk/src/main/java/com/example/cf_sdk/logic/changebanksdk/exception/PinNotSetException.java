package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Thrown when Pin login has not been set by the user.
 */

public class PinNotSetException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.pin_not_set;
    }
}
