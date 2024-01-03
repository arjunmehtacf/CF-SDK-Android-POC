package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by gunveernatt on 9/6/17.
 */

public class BaseException extends Exception {

    public int getErrorResId(){
        return R.string.base_error;
    }

}
