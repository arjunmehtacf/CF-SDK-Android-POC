package com.example.cf_sdk.logic.changebanksdk.validation;

import com.example.cf_sdk.R;
import com.example.cf_sdk.logic.changebanksdk.parameter.member.AddAddressParameters;

import io.reactivex.Single;

/**
 *
 * Address Validator.
 */

public class AddressValidator implements Validator<AddAddressParameters, Single<AddAddressParameters>> {

    private final StringValidator mStringValidator;

    public AddressValidator(StringValidator stringValidator){
        mStringValidator = stringValidator;
    }


    @Override
    public Single<AddAddressParameters> validate(AddAddressParameters input) {
        final ValidationError validationError = new ValidationError();
        if(mStringValidator.validate(input.getStreet())
                && mStringValidator.validate(input.getZipCode())
                && mStringValidator.validate(input.getCity())
                && mStringValidator.validate(input.getState())
                && mStringValidator.validate(input.getCountry())) {
            return Single.just(input);
        } else {
            validationError.addErrorId(R.string.sdk_error_address_invalid);
            return Single.error(validationError);
        }
    }
}
