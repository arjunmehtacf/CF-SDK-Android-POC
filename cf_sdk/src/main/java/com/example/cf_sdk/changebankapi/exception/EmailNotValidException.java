package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 5/11/17.
 *
 * Exception to be thrown when Email input does not meet the requirements.
 */

public class EmailNotValidException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.email_not_valid;
    }
}
