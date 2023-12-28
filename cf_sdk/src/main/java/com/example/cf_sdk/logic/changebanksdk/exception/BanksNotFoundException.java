package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Exception meaning that a bank was not able to be retrieved.
 */

public class BanksNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_banks_not_found;
    }
}
