package com.example.cf_sdk.logic.sdk_module.singleton;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * Android validator.
 */

public class AndroidValidator implements Validator {
    @Override
    public boolean validateEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).find();
    }

    @Override
    public String sanitiseNumberString(String string) {
        String sanitisedNumber = string != null ? string : "";
        return sanitisedNumber.replaceAll("\\D", "");
    }

    @Override
    public boolean validatePhoneNumber(String number) {
        number = sanitiseNumberString(number);
        return number.length() >= PHONE_MINIMUM_LENGTH && number.length() <= PHONE_MAXIMUM_LENGTH;
    }

    @Override
    public boolean validatePinNumber(String pin) {
        return pin != null
                && pin.length() >= PIN_MINIMUM_LENGTH
                && pin.length() <= PIN_MAXIMUM_LENGTH;
    }

    @Override
    public boolean validatePinMatch(String newPin, String confirmPin) {
        return isValidString(newPin) && newPin.equals(confirmPin);
    }

    @Override
    public boolean validatePassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).find();
    }

    @Override
    public boolean validateSmsCode(String code) {
        return isValidString(code);
    }

    /**
     * Validates if amount to cent meets minimum requirements.
     *
     * @param amount amount of cents to be transferred.
     * @return true if amount is at least {@link #MINIMUM_TRANSFER_AMOUNT}
     *         and at max {@link #MAXIMUM_TRANSFER_AMOUNT}.
     */
    boolean validateSendAmountCents(long amount) {
        boolean valid = false;
        if (amount >= 1 && amount <= 50000) {
            valid = true;
        }
        return valid;
    }

    /**
     * Check if String is null or empty.
     *
     * @param charSequence charSequence to validate.
     * @return true if charSequence is not null and is not empty.
     */
    boolean isValidString(CharSequence charSequence) {
        return charSequence != null && !(charSequence.length() == 0);
    }

    /**
     * Validates if String has specified length and contains only numbers.
     *
     * @param charSequence charSequence to validate.
     * @param length length that the charSequence should have.
     * @return true if charSequence has specified length and contains only numbers.
     */
    boolean isNumericStringOfLength(CharSequence charSequence, int length) {
        return isNumericString(charSequence) && isStringOfLength(charSequence, length);
    }

    /**
     * Formats the provided currency into a US format.
     *
     * @param currency amount number to format.
     * @return a string with US currency format.
     */
    String formatCurrency(Double currency) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        if (formatter != null) {
            String symbol = formatter.getCurrency().getSymbol();
            formatter.setNegativePrefix("-" + symbol);
            formatter.setNegativeSuffix("");
            return formatter.format(currency);
        } else {
            return String.format(Locale.US, "$%.02f", currency);
        }
    }

    /**
     * Check if String is not null and has specified length.
     *
     * @param charSequence charSequence to validate.
     * @param length length that the charSequence should have.
     * @return true if charSequence is not null and has specified length
     */
    private boolean isStringOfLength(CharSequence charSequence, int length) {
        return charSequence != null && charSequence.length() == length;
    }

    /**
     * Validates if String contains only numbers.
     *
     * @param charSequence charSequence to validate.
     * @return true if charSequence contains only numbers.
     */
    private boolean isNumericString(CharSequence charSequence) {
        String numbers = charSequence != null? charSequence.toString() : "";
        return NUMBER_ONLY_PATTERN.matcher(numbers).find();
    }
}
