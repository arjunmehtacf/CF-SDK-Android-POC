package com.example.cf_sdk.logic.changebanksdk.model.zendesk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by gunveernatt on 6/6/18.
 */

public class RequestUpdatesMapped implements Serializable {


    @NonNull
    private final Map<String, Integer> mRequestIds;

    public RequestUpdatesMapped(@Nullable Map<String, Integer> requestIds) {
        if(requestIds == null) {
            this.mRequestIds = Collections.emptyMap();
        } else {
            this.mRequestIds = requestIds;
        }

    }

    @NonNull
    public Map<String, Integer> getRequestUpdates() {
        return mRequestIds;
    }

    public boolean isRequestUnread(String requestId) {
        return this.mRequestIds.containsKey(requestId) && ((Integer)this.mRequestIds.get(requestId)).intValue() > 0;
    }

    public boolean hasUpdatedRequests() {
        return !this.mRequestIds.isEmpty();
    }

    public int totalUpdates() {
        int count = 0;

        Integer requestUpdates;
        for(Iterator var2 = this.mRequestIds.values().iterator(); var2.hasNext(); count += requestUpdates.intValue()) {
            requestUpdates = (Integer)var2.next();
        }

        return count;
    }
}
