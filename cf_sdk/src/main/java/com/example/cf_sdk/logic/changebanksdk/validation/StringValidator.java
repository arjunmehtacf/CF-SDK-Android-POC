package com.example.cf_sdk.logic.changebanksdk.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * For edittext space validation.
 */

public class StringValidator implements Validator<String, Boolean> {

    @Override
    public Boolean validate(String input) {
        return input != null && !input.isEmpty();
    }

    public Boolean containsWhiteSpaces(String input) {
        if(input == null){
            return true;
        }
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }


}
