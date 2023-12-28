package com.example.cf_sdk.logic.changebanksdk.parameter.zendesk;

import com.example.cf_sdk.logic.changebanksdk.model.zendesk.SectionMapped;
import com.example.cf_sdk.logic.changebanksdk.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import dagger.internal.Preconditions;

/**
 *
 * Parameters for requesting articles for a particular section.
 */

public class ArticlesForSectionParameters extends Parameters {

    private transient SectionMapped mSectionMapped;

    public static ArticlesForSectionParameters Create(@Nonnull SectionMapped sectionMapped) {
        Preconditions.checkNotNull(sectionMapped);
        return new ArticlesForSectionParameters(new HashMap<String, String>(), sectionMapped);
    }


    private ArticlesForSectionParameters(Map<String, String> headers, SectionMapped sectionMapped) {
        super(headers);
        mSectionMapped = sectionMapped;
    }


    public SectionMapped getSectionMapped() {
        return mSectionMapped;
    }

}
