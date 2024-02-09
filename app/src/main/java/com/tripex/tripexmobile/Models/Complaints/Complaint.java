package com.tripex.tripexmobile.Models.Complaints;

import com.google.gson.annotations.SerializedName;

public class Complaint {
    @SerializedName("id")
    private final String id;

    @SerializedName("complaintFor")
    private final String complaintFor;

    @SerializedName("complaintForName")
    private final String complaintForName;

    @SerializedName("isResolved")
    private final boolean isResolved;

    @SerializedName("resolvedBy")
    private final String resolvedBy;

    @SerializedName("createdById")
    private final String createdBy;

    @SerializedName("complaintLevel")
    private final Integer complaintLevel;

    @SerializedName("complaint")
    private final String complaint;


    public Complaint(String id, String complaintFor, String complaintForName, boolean isResolved, String resolvedBy, String createdBy, Integer complaintLevel, String complaint) {
        this.id = id;
        this.complaintFor = complaintFor;
        this.complaintForName = complaintForName;
        this.isResolved = isResolved;
        this.resolvedBy = resolvedBy;
        this.createdBy = createdBy;
        this.complaintLevel = complaintLevel;
        this.complaint = complaint;
    }


    public String getId() {
        return id;
    }

    public String getComplaintFor() {
        return complaintFor;
    }

    public String getComplaintForName() {
        return complaintForName;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Integer getComplaintLevel() {
        return complaintLevel;
    }

    public String getComplaint() {
        return complaint;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id='" + id + '\'' +
                ", complaintFor='" + complaintFor + '\'' +
                ", isResolved=" + isResolved +
                ", resolvedBy='" + resolvedBy + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", complaintLevel=" + complaintLevel +
                ", complaint='" + complaint + '\'' +
                '}';
    }
}

