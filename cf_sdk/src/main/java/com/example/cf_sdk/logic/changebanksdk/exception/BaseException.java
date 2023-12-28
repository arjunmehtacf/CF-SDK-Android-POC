package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Main base exception class
 */

public class BaseException extends Exception {

    public int getErrorResId(){
        return R.string.base_error;
    }

}
