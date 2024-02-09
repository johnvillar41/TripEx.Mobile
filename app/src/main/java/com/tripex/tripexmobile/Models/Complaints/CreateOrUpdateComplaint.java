package com.tripex.tripexmobile.Models.Complaints;

import com.google.gson.annotations.SerializedName;

public class CreateOrUpdateComplaint {

    @SerializedName("id")
    private final String id;

    @SerializedName("complaintFor")
    private final String complaintFor;

    @SerializedName("createdById")
    private final String createdById;

    @SerializedName("complaint")
    private final String complaint;

    public CreateOrUpdateComplaint(String id, String complaintFor, String createdById, String complaint) {
        this.id = id;
        this.complaintFor = complaintFor;
        this.createdById = createdById;
        this.complaint = complaint;
    }

    public String getId() {
        return id;
    }

    public String getComplaintFor() {
        return complaintFor;
    }

    public String getCreatedById() {
        return createdById;
    }

    public String getComplaint() {
        return complaint;
    }
}
