package com.example.cf_sdk.logic.changebanksdk.parameter.member;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Handles parameters to update member address and name.
 */

public class AddMemberInfoAndAddressParameters extends Parameters {

    @SerializedName("firstName")
    private final String mFirstName;

    @SerializedName("lastName")
    private final String mLastName;

    @SerializedName("address")
    private AddAddressParameters mAddress;

    @SerializedName("dateOfBirth")
    private final String mDateOfBirth;

    @SerializedName("cardType")
    private String mCardType;

    @SerializedName("viewedDocument")
    private boolean viewedDocument = true;

    private transient long mMemberId;


    public static AddMemberInfoAndAddressParameters create(Map<String, String> headers,
                                                           String firstName,
                                                           String lastName,
                                                           String dateOfBirth,
                                                           AddAddressParameters address,
                                                           String physicalCardRequired
                                                           ) {

        return new AddMemberInfoAndAddressParameters(headers, firstName, lastName, dateOfBirth, address,physicalCardRequired);
    }

    public AddMemberInfoAndAddressParameters(Map<String, String> headers,
                                             String firstName,
                                             String lastName,
                                             String dateOfBirth,
                                             AddAddressParameters address,
                                             String physicalCardRequired) {
        super(headers);

        mFirstName = firstName;
        mLastName = lastName;
        mDateOfBirth = dateOfBirth;
        mAddress = address;
        mCardType = physicalCardRequired;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public AddAddressParameters getAddressParameters() {
        return mAddress;
    }

    public void setAddressParameters(AddAddressParameters aap) {
        mAddress = aap;
    }
}
