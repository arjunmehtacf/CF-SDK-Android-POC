package com.example.cf_sdk.changebankapi.parameter.account;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.Map;

/**
 *
 * Delete acg transfer request parameter model class.
 */

public class DeleteAchTransferParameters extends Parameters {

    private transient String mAchTransferId;

    public DeleteAchTransferParameters(Map<String, String> headers, String achTransferId) {
        super(headers);
        mAchTransferId = achTransferId;
    }

    public static DeleteAchTransferParameters create(Map<String, String> headers, String achTransferId) {
        return new DeleteAchTransferParameters(headers, achTransferId);
    }

    public String getAchTransferId() {
        return mAchTransferId;
    }
}
