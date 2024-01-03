package com.example.cf_sdk.changebankapi.parameter.member;


import com.example.sdk_no_dagger.changebankapi.model.member.ImageExtension;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Contains parameters to add profile picture.
 */

public class AddProfilePictureParameters extends Parameters {
    @SerializedName("data")
    private String mData;

    @SerializedName("extension")
    private ImageExtension mExtension;
    private transient long mMemberId;

    public File getFile() {
        return image;
    }

    private File image;

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    private byte[] body;

    public static AddProfilePictureParameters create(ImageExtension imageExtension,
                                                     String imageData, byte[] image) {
        return new AddProfilePictureParameters(new HashMap<String, String>(), imageExtension, imageData,image);
    }

    private AddProfilePictureParameters(Map<String, String> headers, ImageExtension imageExtension, String data,byte[] file) {
        super(headers);

        mExtension = imageExtension;
        mData = data;
        body=file;
    }

    public void setMemberId(long memberId) {
        mMemberId = memberId;
    }

    public long getMemberId() {
        return mMemberId;
    }

    public String getData() {
        return mData;
    }
}
