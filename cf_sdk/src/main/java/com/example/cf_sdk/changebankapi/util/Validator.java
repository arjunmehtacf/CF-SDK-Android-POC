package com.example.cf_sdk.changebankapi.util;

import java.util.regex.Pattern;

/**
 *
 * Validates input strings.
 */

public interface Validator {
    Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
    Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[0-9].*)(?=.*[$@$!%*#?&\\=\\-\\+~\\^])([\\S]{8,})$");
    Pattern NUMBER_ONLY_PATTERN = Pattern.compile("^[0-9]*$");

    int PIN_MINIMUM_LENGTH = 4;
    int PIN_MAXIMUM_LENGTH = 10;
    int PHONE_MINIMUM_LENGTH = 10;
    int PHONE_MAXIMUM_LENGTH = 11;

    long MINIMUM_TRANSFER_AMOUNT = 1;
    long MAXIMUM_TRANSFER_AMOUNT = 50000;

    /**
     * Validates if email has coherence according to {@link Pattern}
     * from {@link Validator#validateEmail(String)}.
     *
     * @param email email string to validate.
     * @return true if email has coherence according to {@link Pattern}
     *         from {@link Validator#validateEmail(String)}.
     */
    boolean validateEmail(String email);

    /**
     * Removes all non-numeric values of the String.
     *
     * @param string string to remove all non-numeric values.
     * @return String containing only the numeric values.
     */
    String sanitiseNumberString(String string);

    boolean validatePhoneNumber(String number);

    /**
     * Validates if PIN number meets minimum requirements.
     *
     * @param pin pin to validate.
     * @return true if pin is at least {@link #PIN_MINIMUM_LENGTH}
     *         and at max {@link #PIN_MAXIMUM_LENGTH}
     */
    boolean validatePinNumber(String pin);

    /**
     * Validates if both pin strings are equals.
     * @param newPin new pin to be set.
     * @param confirmPin confirm the new pin to be set.
     * @return true if both strings are equal.
     */
    boolean validatePinMatch(String newPin, String confirmPin);

    /**
     * Validates if a password meets minimum requirements.
     *
     * @param password password to validate.
     * @return true if password has at least {@link #PASSWORD_PATTERN} and at least
     *         one number, one letter and one special Character.
     */
    boolean validatePassword(String password);

    boolean validateSmsCode(String code);
}
