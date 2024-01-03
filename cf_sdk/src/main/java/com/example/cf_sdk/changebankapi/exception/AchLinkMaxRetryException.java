package com.example.cf_sdk.changebankapi.exception;


import com.example.cf_sdk.R;

/**
 * Created by gunveernatt on 11/10/17.
 *
 * Exception to be thrown when Ach link process exceeds the max retry attempts.
 */

public class AchLinkMaxRetryException extends BaseException {

    @Override
    public int getErrorResId() {
        return R.string.ach_link_max_retry;
    }
}
