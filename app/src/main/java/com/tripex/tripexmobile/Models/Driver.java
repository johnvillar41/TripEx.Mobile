package com.tripex.tripexmobile.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Driver {
    @SerializedName("id")
    private final String id;

    @SerializedName("name")
    private final String name;

    @SerializedName("gender")
    private final String gender;

    @SerializedName("phoneNumber")
    private final String phoneNumber;

    @SerializedName("email")
    private final String email;

    @SerializedName("createdById")
    private final String createdBy;

    @SerializedName("organization")
    private final String organization;

    @SerializedName("images")
    private final List<Image> images;

    public Driver(String id, String name, String gender, String phoneNumber, String email, String createdBy, String organization, List<Image> images) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createdBy = createdBy;
        this.organization = organization;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getOrganization() {
        return organization;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", organization='" + organization + '\'' +
                ", images=" + images +
                '}';
    }
}
