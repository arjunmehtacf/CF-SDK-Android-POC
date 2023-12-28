package com.example.cf_sdk.logic.changebanksdk.model.account;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DepositedCheck implements Serializable {

    private String checkAmount = "100";
    private String checkStatus = "Pending";
    private String date = "05-09-2022";


    public String getCheckAmount() {
        return checkAmount;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public String getDate() {
        return date;
    }

    public String getConvertedDate() {
        String[] separated = date.split("T");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(separated[0], formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dateTime.format(formatter2);
    }
}
