package com.example.cf_sdk.changebankapi.parameter.zendesk;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import com.example.cf_sdk.changebankapi.model.zendesk.SectionMapped;
import com.example.cf_sdk.changebankapi.parameter.Parameters;

import java.util.HashMap;
import java.util.Map;



/**
 *
 * Parameters for requesting articles for a particular section.
 */

public class ArticlesForSectionParameters extends Parameters {

    private transient SectionMapped mSectionMapped;

    public static ArticlesForSectionParameters Create(@NonNull SectionMapped sectionMapped) {

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
