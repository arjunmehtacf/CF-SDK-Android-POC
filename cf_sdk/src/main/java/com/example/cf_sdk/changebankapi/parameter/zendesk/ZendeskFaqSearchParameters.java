package com.example.cf_sdk.changebankapi.parameter.zendesk;


import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;



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
