package com.example.cf_sdk.logic.changebanksdk.parameter.zendesk;

import com.example.cf_sdk.logic.changebanksdk.model.zendesk.CategoryMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import dagger.internal.Preconditions;

/**
 *
 * Parameters for getting sections for a particular category.
 */

public class SectionsForCategoryParameters extends Parameters {

    private transient CategoryMapped mCategoryMapped;

    public static SectionsForCategoryParameters Create(@Nonnull CategoryMapped categoryMapped) {
        Preconditions.checkNotNull(categoryMapped);
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
