package com.example.cf_sdk.logic.changebanksdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Model calss for document upload response.
 */
public class DocumentUploadResponse extends Throwable {
    @SerializedName("messages")
    private List<ResponseMessage> mMessageList = new ArrayList<>();

    @SerializedName("message")
    private String message = "";

    private int httpCode;


    public static DocumentUploadResponse create() {
        return new DocumentUploadResponse();
    }

    private DocumentUploadResponse() {

    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }


    public void setMessage(String message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setDefaultMessage(message);
        mMessageList.add(0, responseMessage);
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getMessages() {
        if (mMessageList != null && !mMessageList.isEmpty()) {
            return mMessageList.get(0).getDefaultMessage();
        }
        return "";
    }

    public String getKey() {
        if (mMessageList != null && !mMessageList.isEmpty()) {
            return mMessageList.get(0).getMessageKey();
        }
        return "";
    }


    public String getMessage(){
        return message;
    }

}
