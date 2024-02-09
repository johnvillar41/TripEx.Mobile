package com.tripex.tripexmobile.Views.Interfaces;

import com.tripex.tripexmobile.Models.Complaints.Complaint;

import java.util.List;

public interface IComplaintView extends IBaseView {
    void displayComplaints(List<Complaint> complaints);

    int getPageNumber();

    int getPageSize();

    void displayFilterSettings();

    void setTotalPageSize(int pageSize);
}
