package com.example.cf_sdk.logic.changebanksdk.parameter.account;

import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;

/**
 *
 * Parameters for {@link com.example.cf_sdk.logic.changebanksdk.task.account.GetCardByIdTask};
 */

public class CardParameters extends Parameters {
    private String mCardId;

    public static CardParameters create(String cardId) {
        return new CardParameters(new HashMap<String, String>(), cardId);
    }

    private CardParameters(HashMap<String, String> headers, String cardId) {
        super(headers);

        mCardId = cardId;
    }

    public String getCardId() {
        return mCardId;
    }
}
