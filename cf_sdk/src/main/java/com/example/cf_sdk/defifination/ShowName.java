package com.example.cf_sdk.defifination;

import com.example.cf_sdk.logic.GetVerifiedName;

public class ShowName {

    private static IVerificationProvider verificationProvider = new GetVerifiedName();

    public String getName(){
        return "Welcome to Change Financial SDK POC";
    }

    public static String authenticateUser(String name) {
        return "Welcome "+name+ " You are " + verificationProvider.verifiedName(name) + " user to use to this library";
    }
}
