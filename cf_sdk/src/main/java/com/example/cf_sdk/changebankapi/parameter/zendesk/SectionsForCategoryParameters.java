package com.example.cf_sdk.changebankapi.parameter.zendesk;


import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import com.example.cf_sdk.changebankapi.model.zendesk.CategoryMapped;
import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;



/**
 *
 * Parameters for getting sections for a particular category.
 */

public class SectionsForCategoryParameters extends Parameters {

    private transient CategoryMapped mCategoryMapped;

    public static SectionsForCategoryParameters Create(@NonNull CategoryMapped categoryMapped) {

        return new SectionsForCategoryParameters(new HashMap<String, String>(), categoryMapped);
    }


    private SectionsForCategoryParameters(Map<String, String> headers, CategoryMapped categoryMapped) {
        super(headers);
        mCategoryMapped = categoryMapped;
    }


    public CategoryMapped getCategoryMapped() {
        return mCategoryMapped;
    }

}
