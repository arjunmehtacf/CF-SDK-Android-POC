package com.example.cf_sdk.changebankapi.parameter.account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.cf_sdk.changebankapi.parameter.Parameters;
import com.example.sdk_no_dagger.changebankapi.model.account.CardStatus;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;



/**
 *
 * Parameters for updating the card status
 */

public class UpdateCardStatusParameters extends Parameters {

    @SerializedName("status")
    private final CardStatus mStatus;

    private final transient String mCardId;

    private transient String mAccountNumber;



    @SerializedName("blockOption")
    private String blockOption;

    public String getCardNumber() {
        return cardNumber;
    }

    @SerializedName("cardNumber")
    private String cardNumber;

    public String getBlockOption() {
        return blockOption;
    }

    public static UpdateCardStatusParameters create(String cardId, CardStatus cardStatus) {
        return new UpdateCardStatusParameters(new HashMap<String, String>(), cardId, cardStatus);
    }

    private UpdateCardStatusParameters(Map<String, String> headers, String cardId, CardStatus cardStatus) {
        super(headers);
        mCardId = cardId;
        mStatus = cardStatus;
    }
    private UpdateCardStatusParameters(Map<String, String> headers, String option, String token) {
        super(headers);
        blockOption = option;
        cardNumber = token;
        mCardId = "";
        mStatus = CardStatus.BLOCKED;
    }

    public static UpdateCardStatusParameters create(String option, String token) {
        return new UpdateCardStatusParameters(new HashMap<String, String>(), option, token);
    }

    private UpdateCardStatusParameters(Map<String, String> headers,
                                       String cardId,
                                       CardStatus cardStatus,
                                       String accountNumber) {
        super(headers);
        mCardId = cardId;
        mStatus = cardStatus;
        mAccountNumber = accountNumber;
    }

    public UpdateCardStatusParameters withAccountNumber(String accountNumber) {
        return new UpdateCardStatusParameters(
                getHeaders(),
                mCardId,
                mStatus,
                accountNumber
        );
    }

    @NonNull
    public CardStatus getStatus() {
        return mStatus;
    }

    @NonNull
    public String getCardId() {
        return mCardId;
    }

    @Nullable
    public String getAccountNumber() {
        return mAccountNumber;
    }
}
