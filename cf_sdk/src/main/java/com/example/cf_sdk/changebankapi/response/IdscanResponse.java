package com.example.cf_sdk.changebankapi.response;


import com.example.cf_sdk.changebankapi.model.member.Address;
import com.google.gson.annotations.SerializedName;



public class IdscanResponse {
    @SerializedName("firstName")
    private String mFirstName;

    @SerializedName("lastName")
    private String mLastName;

    @SerializedName("dateOfBirth")
    private String mDob;

    @SerializedName("address")
    private Address mAddress;

    public static IdscanResponse create(
            String firstName,
            String lastName,
            String dob,
            Address address) {
        return new IdscanResponse(firstName, lastName, dob, address);
    }

    private IdscanResponse(String firstName, String lastName, String dob, Address address) {
        mFirstName = firstName;
        mLastName = lastName;
        mDob = dob;
        mAddress = address;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getDob() {
        return mDob;
    }

    public Address getAddress() {
        return mAddress;
    }
}
