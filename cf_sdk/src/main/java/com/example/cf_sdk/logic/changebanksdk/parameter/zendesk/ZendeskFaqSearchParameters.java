package com.example.cf_sdk.logic.changebanksdk.parameter.zendesk;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gunveernatt on 6/4/18.
 */

public class ZendeskFaqSearchParameters extends Parameters {

    private transient String mSearchQuery;

    public static ZendeskFaqSearchParameters Create(String searchQuery) {
        return new ZendeskFaqSearchParameters(new HashMap<String, String>(), searchQuery);
    }

    public ZendeskFaqSearchParameters(Map<String, String> headers, String searchQuery) {
        super(headers);
        mSearchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return mSearchQuery;
    }
}
