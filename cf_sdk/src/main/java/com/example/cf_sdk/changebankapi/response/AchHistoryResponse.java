package com.example.cf_sdk.changebankapi.response;


import com.example.cf_sdk.changebankapi.model.account.AchTransfer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Representation of the {@link AchTransfer} history response.
 */

public class AchHistoryResponse {

    public static AchHistoryResponse create(List<AchTransfer> achTransfers) {
        return new AchHistoryResponse(achTransfers);
    }

    public AchHistoryResponse(List<AchTransfer> achTransferHistory) {
        mAchTransferHistory = achTransferHistory;
    }

    @SerializedName("transactions")
    private List<AchTransfer> mAchTransferHistory = null;

    public List<AchTransfer> getAchTransferHistory() {
        return mAchTransferHistory;
    }


}
