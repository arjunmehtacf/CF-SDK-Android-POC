package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 *
 * Member credentials exception to be thrown when {@link com.changefinancials.changebanksdk.model.Session}
 * expire.
 */

public class SessionExpiredException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.member_credentials_expired;
    }
}
