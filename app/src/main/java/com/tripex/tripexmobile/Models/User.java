package com.tripex.tripexmobile.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private final String id;

    @SerializedName("username")
    private final String username;

    @SerializedName("image")
    private final String image;

    @SerializedName("fullName")
    private final String fullName;

    @SerializedName("password")
    private final String password;

    @SerializedName("email")
    private final String email;

    @SerializedName("roleId")
    private final String roleId;

    public User(String id, String username, String image, String fullName, String password, String email, String roleId) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }
}
