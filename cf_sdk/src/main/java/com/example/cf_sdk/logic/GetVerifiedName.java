package com.example.cf_sdk.logic;

import com.example.cf_sdk.defifination.IVerificationProvider;

public class GetVerifiedName implements IVerificationProvider {
    public String verifiedName = "";


    @Override
    public String verifiedName(String name) {
        verifiedName = getVerifiedName(name);
        return verifiedName;
    }

    private String getVerifiedName(String name) {
        if (name.equalsIgnoreCase("Arjun Mehta") || name.equalsIgnoreCase("Atul Parmar")) {
            return " Verified";
        } else {
            return " Not Verified";
        }
    }
}
