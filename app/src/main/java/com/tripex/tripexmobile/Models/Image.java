package com.tripex.tripexmobile.Models;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    private final String id;

    @SerializedName("imageLink")
    private final String imageLink;

    public Image(String id, String imageLink) {
        this.id = id;
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
