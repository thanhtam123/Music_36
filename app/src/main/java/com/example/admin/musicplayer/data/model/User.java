package com.example.admin.musicplayer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TamTT on 9/14/2018.
 */

public class User {
    @SerializedName("avatar_url")
    @Expose
    private String mAvatarUrl;
    @SerializedName("first_name")
    @Expose
    private String mFirstName;
    @SerializedName("full_name")
    @Expose
    private String mFullName;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("last_modified")
    @Expose
    private String mLastModified;
    @SerializedName("last_name")
    @Expose
    private String mLastName;
    @SerializedName("permalink")
    @Expose
    private String mPermalink;
    @SerializedName("permalink_url")
    @Expose
    private String mPermalinkUrl;
    @SerializedName("uri")
    @Expose
    private String mUri;
    @SerializedName("urn")
    @Expose
    private String mUrn;
    @SerializedName("username")
    @Expose
    private String mUsername;
    @SerializedName("verified")
    @Expose
    private boolean mVerified;
    @SerializedName("city")
    @Expose
    private String mCity;
    @SerializedName("country_code")
    @Expose
    private String mCountryCode;

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getLastModified() {
        return mLastModified;
    }

    public void setLastModified(String lastModified) {
        mLastModified = lastModified;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPermalink() {
        return mPermalink;
    }

    public void setPermalink(String permalink) {
        mPermalink = permalink;
    }

    public String getPermalinkUrl() {
        return mPermalinkUrl;
    }

    public void setPermalinkUrl(String permalinkUrl) {
        mPermalinkUrl = permalinkUrl;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public String getUrn() {
        return mUrn;
    }

    public void setUrn(String urn) {
        mUrn = urn;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public boolean isVerified() {
        return mVerified;
    }

    public void setVerified(boolean verified) {
        mVerified = verified;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "mAvatarUrl='" + mAvatarUrl + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mFullName='" + mFullName + '\'' +
                ", mId=" + mId +
                ", mKind='" + mKind + '\'' +
                ", mLastModified='" + mLastModified + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mUsername='" + mUsername + '\'' +
                '}';
    }
}
