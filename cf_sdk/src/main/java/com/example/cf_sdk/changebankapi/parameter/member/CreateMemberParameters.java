package com.example.cf_sdk.changebankapi.parameter.member;


import com.example.cf_sdk.changebankapi.exception.EmailNotValidException;
import com.example.cf_sdk.changebankapi.exception.PasswordNotValidException;
import com.example.cf_sdk.changebankapi.exception.PhoneNotValidException;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.cf_sdk.changebankapi.util.Validator;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 *
 * Handles parameters to make a successful request to create a member.
 */

public class CreateMemberParameters extends Parameters {
    @SerializedName("applicationID")
    private final String mApplicationId;

    @SerializedName("phoneNumber")
    private final String mPhone;

    @SerializedName("password")
    private final String mPassword;

    @SerializedName("username")
    private final String mUsername;

    @SerializedName("firstName")
    private final String mFirstName;

    @SerializedName("lastName")
    private final String mLastName;

    @SerializedName("emailAddress")
    private final String mEmail;

   /* @SerializedName("panLast4")
    private final String mPanLast4;

    @SerializedName("adminNo")
    private final String mAdminNO;*/

    private transient final boolean mRememberUsername;

    public boolean isRememberUsername() {
        return mRememberUsername;
    }

    public String getUsername() {
        return mUsername;
    }

    public static CreateMemberParameters create(Map<String, String> headers,
                                                Validator validator,
                                                String phone,
                                                String password,
                                                String firstName,
                                                String lastName,
                                                String email,
                                                String username,
                                                String adminNo,
                                                String pan4,
                                                boolean rememberUsername,
                                                String applicationId)
            throws PhoneNotValidException, EmailNotValidException, PasswordNotValidException {
        if (!validator.validatePhoneNumber(phone)) throw new PhoneNotValidException();
        if (!validator.validatePassword(password)) throw new PasswordNotValidException();
        if (!validator.validateEmail(email)) throw new EmailNotValidException();
        return new CreateMemberParameters(headers, phone, password, firstName, lastName, email, username, adminNo, pan4, rememberUsername,applicationId);
    }

    public CreateMemberParameters(Map<String, String> headers, String phone, String password,
                                  String firstName, String lastName, String email, String username, String adminNumber, String pan4, boolean rememberUsername,String applicationId) {
        super(headers);

        mPhone = phone;
        mPassword = password;
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mUsername = username != null ? username.toLowerCase() : null;
        mRememberUsername = rememberUsername;
//        mAdminNO = adminNumber.isEmpty() ? null : adminNumber;
//        mPanLast4 = pan4.isEmpty() ? null : pan4;
        mApplicationId = applicationId;

    }


}
