package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 *
 * Exception meaning that a card was not able to be retrieved.
 */

public class CardNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_card_not_found;
    }
}
