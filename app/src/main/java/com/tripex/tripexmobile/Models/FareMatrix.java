package com.tripex.tripexmobile.Models;

import com.google.gson.annotations.SerializedName;

public class FareMatrix {
    @SerializedName("id")
    private final String id;

    @SerializedName("distance")
    private final int distance;

    @SerializedName("regularFare")
    private final double regularFare;

    @SerializedName("discountedFare")
    private final double discountedFare;

    public FareMatrix(String id, int distance, double regularFare, double discountedFare, String vehicleTypeId) {
        this.id = id;
        this.distance = distance;
        this.regularFare = regularFare;
        this.discountedFare = discountedFare;
    }

    public String getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public double getRegularFare() {
        return regularFare;
    }

    public double getDiscountedFare() {
        return discountedFare;
    }
}
