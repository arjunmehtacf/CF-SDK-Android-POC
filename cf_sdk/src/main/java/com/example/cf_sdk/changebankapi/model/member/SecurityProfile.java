package com.example.cf_sdk.changebankapi.model.member;


import com.example.cf_sdk.changebankapi.model.Features;
import com.example.sdk_no_dagger.changebankapi.model.Role;
import com.example.sdk_no_dagger.changebankapi.model.member.SecurityLevel;
import com.google.gson.annotations.SerializedName;

/**
 *
 * Model for member Security Profile.
 */

public class SecurityProfile {

    @SerializedName("memberId")
    private Integer mMemberId=0;

    @SerializedName("role")
    private Role mRole;

    @SerializedName("features")
    private Features mFeatures;

    @SerializedName("locked")
    private Boolean mLocked=false;

    @SerializedName("securityLevel")
    private SecurityLevel mSecurityLevel;

    private SecurityProfile(SecurityLevel securityLevel) {
        mSecurityLevel = securityLevel;
    }

    public Integer getMemberId() {
        return mMemberId;
    }

    public Role getRole() {
        return mRole;
    }

    public Features getFeatures() {
        return mFeatures;
    }

    public Boolean getLocked() {
        return mLocked;
    }

    public SecurityLevel getSecurityLevel() {
        return mSecurityLevel;
    }

    public static SecurityProfile CreateWithSecurityLevel(SecurityLevel securityLevel){
        return new SecurityProfile(securityLevel);
    }


}
