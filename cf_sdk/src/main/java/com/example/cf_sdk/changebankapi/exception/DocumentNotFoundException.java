package com.example.cf_sdk.changebankapi.exception;

import com.example.cf_sdk.R;

/**
 * Created by victorojeda on 5/28/18.
 *
 * Exception meaning that a document was not able to be retrieved.
 */

public class DocumentNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_document_not_found;
    }
}
