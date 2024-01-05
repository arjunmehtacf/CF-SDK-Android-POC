package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;


public class BaseException extends Exception {

    public int getErrorResId(){
        return R.string.base_error;
    }

}
