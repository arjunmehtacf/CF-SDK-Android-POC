package com.example.cf_sdk.changebankapi.model.zendesk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;
import java.util.Map;

/**
 *
 */

class UserMapped {


    private Long mId=0L;
    private String mName = "";
    private AttachmentMapped mPhoto;
    private boolean mAgent=false;
    private Long mOrganizationId=0L;
    private List<String> mTags;
    private Map<String,String> mUserFields;

    /**
     * Initialises a user with given values.
     */
    public UserMapped(Long id, String name, AttachmentMapped photo, boolean agent, Long organizationId, List<String> tags, Map<String, String> userFields) {
        mId = id;
        mName = name;
        mPhoto = photo;
        mAgent = agent;
        mOrganizationId = organizationId;
        mTags = tags;
        mUserFields = userFields;
    }

    /**
     * Get the id of the user.
     *
     * @return the user's Id
     */
    @Nullable
    public Long getId() {
        return mId;
    }

    /**
     * Get the name of the user.
     *
     * @return the user's name
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * Get avatar of the user.
     *
     * @return the user's avatar
     */
    @Nullable
    public AttachmentMapped getPhoto() {
        return mPhoto;
    }

    /**
     * Is the user an agent.
     *
     * @return true if the user is an agent
     */
    public boolean isAgent() {
        return mAgent;
    }

    /**
     * Get the organization id of the user.
     *
     * @return the organization id of the user
     */
    @Nullable
    public Long getOrganizationId() {
        return mOrganizationId;
    }

    /**
     * Gets the list of tags associated with the user
     *
     * @return the list of tags associated with the user
     */
    @NonNull
    public List<String> getTags() {
        return mTags;
    }

    /**
     * Get a map of user fields, associated with the user.
     *
     * @return A map containing user fields.
     */
    @NonNull
    public Map<String, String> getUserFields() {
        return mUserFields;
    }



}
