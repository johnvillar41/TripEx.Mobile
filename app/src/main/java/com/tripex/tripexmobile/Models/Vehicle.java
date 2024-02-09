package com.tripex.tripexmobile.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Vehicle {
    @SerializedName("id")
    private final String id;

    @SerializedName("name")
    private final String name;

    @SerializedName("plateNumber")
    private final String plateNumber;

    @SerializedName("createdById")
    private final String createdBy;

    @SerializedName("vehicleType")
    private final String vehicleType;

    /*@SerializedName("dateCreated")
    private final Date dateCreated;*/

    @SerializedName("images")
    private final List<Image> images;

    @SerializedName("fareMatrices")
    private final List<FareMatrix> fareMatrices;

    public Vehicle(String id, String name, String plateNumber, String createdBy, String vehicleType, Date dateCreated, List<Image> images, List<FareMatrix> fareMatrices) {
        this.id = id;
        this.name = name;
        this.plateNumber = plateNumber;
        this.createdBy = createdBy;
        this.vehicleType = vehicleType;
        //.dateCreated = dateCreated;
        this.images = images;
        this.fareMatrices = fareMatrices;
    }

    public List<FareMatrix> getFareMatrices() {
        return fareMatrices;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", images=" + images +
                '}';
    }
}
