package com.example.cf_sdk.logic.changebanksdk.validation;

import com.example.cf_sdk.R;
import com.example.cf_sdk.logic.changebanksdk.parameter.account.AchTransferParameters;

import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 *
 * Ach Transfer Validator.
 */

public class AchTransferValidator implements Validator<AchTransferParameters, Single<AchTransferParameters>> {


    @Override
    public Single<AchTransferParameters> validate(AchTransferParameters input) {
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(input.getTransferType());
        final ValidationError validationError = new ValidationError();

//        if (input.getTransferType() == AchTransferType.BANK_TO_CARD) {
            if (input.getAchAccount() == null) {
                validationError.addErrorId(R.string.sdk_error_transfer_to_required);
            }
            if (input.getAccount() == null) {
                validationError.addErrorId(R.string.sdk_error_transfer_from_required);
            }
//        }


        if (input.getAmount() == null || Double.parseDouble(input.getAmount()) == 0) {
            validationError.addErrorId(R.string.sdk_error_transfer_amount_required);
        }

        if (validationError.hasError()) {
            return Single.error(validationError);
        } else {
            return Single.just(input);
        }
    }
}
