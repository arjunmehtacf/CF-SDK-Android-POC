package com.example.cf_sdk.logic.changebanksdk.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Generic error return when a validation does not succeed.
 */

public class ValidationError extends Exception {
    private final ArrayList<Integer> mErrorIds = new ArrayList<>();

    public ValidationError() {
        super();
    }

    void addErrorId(int errorId) {
        mErrorIds.add(errorId);
    }

    boolean hasError() {
        return !mErrorIds.isEmpty();
    }

    public List<Integer> getErrorIds() {
        return mErrorIds;
    }
}
