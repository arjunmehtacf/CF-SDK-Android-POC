package com.example.cf_sdk.logic.changebanksdk.model.member;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response model class for user profile
 */
public class UserProfileResponse {

    @SerializedName("applicationID")
    @Expose
    private String applicationID;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("firstName")
    @Expose
    private String firstName;

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("customerNumber")
    @Expose
    private String customerNumber;


}
