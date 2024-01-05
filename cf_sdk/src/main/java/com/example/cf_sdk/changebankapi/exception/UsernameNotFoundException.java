package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 *
 * Exception meaning that a username was not able to be retrieved.
 */

public class UsernameNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_username_not_found;
    }
}
