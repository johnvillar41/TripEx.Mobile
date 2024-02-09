package com.tripex.tripexmobile.Views.Interfaces;

import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.Models.Complaints.CreateOrUpdateComplaint;
import com.tripex.tripexmobile.Models.Driver;

public interface IQrScannerView extends IBaseView {
    boolean validateFields();

    void initializeScanner();

    void displayDriver(Driver result);

    CreateOrUpdateComplaint getComplaint();

    void displaySuccessMessage();
}
