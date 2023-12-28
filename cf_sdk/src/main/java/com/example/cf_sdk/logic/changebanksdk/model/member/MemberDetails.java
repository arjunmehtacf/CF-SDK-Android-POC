package com.example.cf_sdk.logic.changebanksdk.model.member;

import android.os.Build;
import android.telephony.PhoneNumberUtils;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Details for a ChangeBank member.
 */

public class MemberDetails {
    private static final String BLANK_SPACE = " ";

    @SerializedName("registrationStatus")
    private RegistrationStatus mRegistrationStatus;

    @SerializedName("selectedID")
    private String mId;

    @SerializedName("socialSecurity")
    private String mSocialSecurity;

    @SerializedName("emailAddress")
    private final String mEmail;

    @SerializedName("username")
    private final String mUsername;

    @SerializedName("firstName")
    private final String mFirstName;

    @SerializedName("lastName")
    private final String mLastName;

    @SerializedName("phoneNumber")
    private final String mPhone;

    @SerializedName("address")
    private final Address mAddress;

    @SerializedName("dateOfBirth")
    private final String mDateOfBirth;

    @SerializedName("dateCreated")
    private final String mMemberCreatedDate;

    @SerializedName("profilePicture")
    private final String mProfilePicture;

    @SerializedName("migrated")
    private final boolean mMigrated;

    @SerializedName("pan4")
    private final String pan4 ="1234";

    @SerializedName("adminNumber")
    private final String adminNumber = "adsf";

    @SerializedName("displayDocument")
    private final boolean mDisplayDocument = false;
    @SerializedName("fullname")
    private final String fullName;

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPhoneMasked() {
        return phoneMasked;
    }

    @SerializedName("accountNumber")
    private final String accountNumber;

    @SerializedName("phoneMasked")
    private final String phoneMasked;


    public static MemberDetails create(String id) {
        return new MemberDetails(id);
    }

    private MemberDetails(String id) {
        mId = id;
        mEmail = "";
        mUsername = "";
        mFirstName = "";
        mLastName = "";
        mPhone = "";
        mAddress = Address.EMPTY;
        mDateOfBirth = "";
        mMemberCreatedDate = "";
        mProfilePicture = null;
        mMigrated = false;
        fullName = "";
        accountNumber = "";
        phoneMasked = "";
    }

    public static MemberDetails Create(String id,
                                       String firstName,
                                       String lastName,
                                       String phone,
                                       String createdDate) {
        return new MemberDetails(id, null, null, firstName, lastName, phone, null, null, createdDate, null, null, false,"","","");
    }

    public static MemberDetails Create(String id,
                                       String firstName,
                                       String lastName,
                                       String phone,
                                       String createdDate,
                                       String profilePicture) {
        return new MemberDetails(id, null, null, firstName, lastName, phone, null, null, createdDate, profilePicture, null, false,"","","");
    }


    public static MemberDetails Create(String id,
                                       String firstName,
                                       String lastName,
                                       String phone,
                                       String createdDate,
                                       String email,
                                       Address address,
                                       String profilePicture) {
        return new MemberDetails(id, email, null, firstName, lastName, phone, address, null, createdDate, profilePicture, null, false,"","","");
    }

    private MemberDetails(String id,
                          String email,
                          String username,
                          String firstName,
                          String lastName,
                          String phone,
                          Address address,
                          String dateOfBirth,
                          String createdDate,
                          String profilePicture, RegistrationStatus registrationStatus, boolean migrated,String fullname,String accountNumber,String phonemask) {
        mId = id;
        mEmail = email;
        mUsername = username;
        mFirstName = firstName;
        mLastName = lastName;
        mPhone = phone;
        mAddress = address;
        mDateOfBirth = dateOfBirth;
        mMemberCreatedDate = createdDate;
        mProfilePicture = profilePicture;
        mRegistrationStatus = registrationStatus;
        mMigrated = migrated;
        this.fullName = fullname;
        this.accountNumber =accountNumber;
        phoneMasked = phonemask;
    }

    public String convertAddressToString() {
        if (mAddress != null) {
            StringBuilder sb = new StringBuilder(mAddress.getAddressLine1());
            if (mAddress.getAddressLine2() != null && !mAddress.getAddressLine2().trim().isEmpty()) {
                sb.append(BLANK_SPACE);
                sb.append("\n");
                sb.append(mAddress.getAddressLine2());
            }
            sb.append(BLANK_SPACE);
            sb.append(mAddress.getCity());
            sb.append(", ");
            sb.append(mAddress.getState());
            sb.append(BLANK_SPACE);
            sb.append(mAddress.getZipCode());

            return sb.toString();
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberDetails that = (MemberDetails) o;

        return mId != null ? mId.equals(that.mId) : that.mId == null;
    }

    @Override
    public int hashCode() {
        return mId != null ? mId.hashCode() : 0;
    }

    public String getId() {
        return mId;
    }

    public MemberDetails withId(String id) {
        return new MemberDetails(
                id,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getEmail() {
        return mEmail;
    }

    public MemberDetails withEmail(String email) {
        return new MemberDetails(
                mId,
                email,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public MemberDetails withProfilePicture(String profilePicture) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                profilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getUsername() {
        return mUsername;
    }

    public MemberDetails withUsername(String username) {
        return new MemberDetails(
                mId,
                mEmail,
                username,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getFirstName() {
        return mFirstName;
    }

    public MemberDetails withFirstName(String firsName) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                firsName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getLastName() {
        return mLastName;
    }

    public MemberDetails withLastName(String lastName) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                lastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getPhone() {
        if(mPhone != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return PhoneNumberUtils.formatNumber(mPhone, "US");
            }
        }
        return mPhone;
    }

    public MemberDetails withPhone(String phone) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                phone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public Address getAddress() {
        return mAddress;
    }

    public MemberDetails withAddress(Address address) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                address,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    public MemberDetails withDateOfBirth(String dateOfBirth) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                dateOfBirth,
                mMemberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getMemberCreatedDate() {
        return mMemberCreatedDate;
    }

    public MemberDetails withMemberCreatedDate(String memberCreatedDate) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                memberCreatedDate,
                mProfilePicture, mRegistrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public MemberDetails withRegistrationStatus(RegistrationStatus registrationStatus) {
        return new MemberDetails(
                mId,
                mEmail,
                mUsername,
                mFirstName,
                mLastName,
                mPhone,
                mAddress,
                mDateOfBirth,
                mMemberCreatedDate,
                mProfilePicture,
                registrationStatus, mMigrated,fullName,accountNumber,phoneMasked);
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public int getMemberCreatedYear() {
        DateTime date = DateTime.parse(mMemberCreatedDate,
                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));
        return date.getYear();
    }

    public String getMemberFullName() {
        StringBuilder nameBuilder = new StringBuilder();

        if (mFirstName != null && !mFirstName.trim().isEmpty()) {
            nameBuilder.append(mFirstName.trim());
            nameBuilder.append(" ");
        }

        if (mLastName != null && !mLastName.trim().isEmpty()) {
            nameBuilder.append(mLastName.trim());
        }

        return nameBuilder.toString();
    }

    public long getMemberCreatedDateLong() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
        Date date = null;
        try {
            if(mMemberCreatedDate != null){
                date = df.parse(mMemberCreatedDate);
            }else{
                date = new Date();
            }
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 1L;
    }

    public RegistrationStatus getRegistrationStatus() {
        return mRegistrationStatus;
    }

    public boolean checkIsIdScanEnabled() {
        if(mRegistrationStatus != null){
            return mRegistrationStatus.isIdScanEnabled();
        }

        return false;
    }

    public String getSocialSecurity() {
        return mSocialSecurity;
    }

    public String getPan4(){
        return pan4;
    }

    public String getAdminNumber(){
        return adminNumber;
    }

    public boolean isDisplayDocument(){
        return mDisplayDocument;
    }

    public boolean isMigrated() {
        return mMigrated;
    }

}
