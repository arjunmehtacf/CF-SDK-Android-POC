package com.example.cf_sdk.changebankapi.response;


import com.example.cf_sdk.changebankapi.model.account.Credential;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 *
 * Api response for the fields required to for ach linking of external bank.
 */

public class BankCredentialsApiResponse {
    @SerializedName("credentials")
    private ArrayList<Credential> mCredentials = null;

    public static BankCredentialsApiResponse create(ArrayList<Credential> credentials) {
        return new BankCredentialsApiResponse(credentials);
    }

    public BankCredentialsApiResponse(ArrayList<Credential> credentials) {
        mCredentials = credentials;
    }

    public ArrayList<Credential> getCredentials() {
        return mCredentials;
    }
}