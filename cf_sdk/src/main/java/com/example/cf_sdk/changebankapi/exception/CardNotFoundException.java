package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 5/28/18.
 *
 * Exception meaning that a card was not able to be retrieved.
 */

public class CardNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_card_not_found;
    }
}
