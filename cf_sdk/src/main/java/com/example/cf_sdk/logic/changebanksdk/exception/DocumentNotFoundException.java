package com.example.cf_sdk.logic.changebanksdk.exception;

import com.example.cf_sdk.R;
/**
 *
 * Exception meaning that a document was not able to be retrieved.
 */

public class DocumentNotFoundException extends BaseException {
    @Override
    public int getErrorResId() {
        return R.string.sdk_error_document_not_found;
    }
}
