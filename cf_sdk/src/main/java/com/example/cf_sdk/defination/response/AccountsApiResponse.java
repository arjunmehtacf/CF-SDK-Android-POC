package com.example.cf_sdk.defination.response;


import com.example.cf_sdk.changebankapi.model.account.Account;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Response to fetch a list of accounts.
 */

public class AccountsApiResponse {


    @SerializedName("title")
    private String title;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("middleName")
    private String middleName;

    @SerializedName("lastName")
    private String lastName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    @SerializedName("customerNumber")
    private String customerNumber;

    @SerializedName("cards")
    private List<Account> mAccounts = null;

    public static AccountsApiResponse create(List<Account> accounts) {
        return new AccountsApiResponse(accounts);
    }

    private AccountsApiResponse(List<Account> accounts) {
        mAccounts = accounts;
    }

    public List<Account> getAccounts() {
        return mAccounts;
    }

    public String toRawString() {
        return "title='" + title + '\'' +
                ", \nfirstName='" + firstName + '\'' +
                ", \nmiddleName='" + middleName + '\'' +
                ", \nlastName='" + lastName + '\'' +
                ", \ncustomerNumber='" + customerNumber + '\'';
    }
}
