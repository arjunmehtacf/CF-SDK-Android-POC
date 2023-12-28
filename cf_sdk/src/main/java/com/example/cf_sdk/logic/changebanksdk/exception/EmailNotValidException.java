package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Exception to be thrown when Email input does not meet the requirements.
 */

public class EmailNotValidException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.email_not_valid;
    }
}
