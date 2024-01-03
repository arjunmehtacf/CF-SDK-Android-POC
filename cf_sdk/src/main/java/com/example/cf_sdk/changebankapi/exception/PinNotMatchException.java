package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 11/20/16.
 *
 * Thrown when PINs provided by user do not match.
 */

public class PinNotMatchException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.pin_incorrect;
    }
}
