package com.example.cf_sdk.logic.changebanksdk.exception;


import com.example.cf_sdk.R;

/**
 *
 * Exception to be thrown when Ach link process exceeds the max retry attempts.
 */

public class AchLinkMaxRetryException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.ach_link_max_retry;
    }
}
