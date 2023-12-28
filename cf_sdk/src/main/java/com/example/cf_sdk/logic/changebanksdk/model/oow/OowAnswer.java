package com.example.cf_sdk.logic.changebanksdk.model.oow;

import com.google.gson.annotations.SerializedName;

/**
 *
 * Oow answer.
 */

public class OowAnswer {
    @SerializedName("type")
    private String mType;

    @SerializedName("answer")
    private String mAnswer;

    public static OowAnswer create(String type, String answer) {
        return new OowAnswer(type, answer);
    }

    private OowAnswer(String type, String answer) {
        mType = type;
        mAnswer = answer;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        this.mAnswer = answer;
    }
}
