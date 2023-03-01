package com.tripex.tripexmobile.Presenters;

import com.tripex.tripexmobile.Services.IQrScannerService;
import com.tripex.tripexmobile.Views.IQrScannerView;

public class QrScannerPresenter {

    private final IQrScannerView view;
    private final IQrScannerService service;

    public QrScannerPresenter(IQrScannerView view, IQrScannerService service) {
        this.view = view;
        this.service = service;
    }

    public void onLoad() {
        if (view.requestCamera()) {
            view.displayCamera();
        }
    }

    public void startCamera() {
        view.displayCamera();
    }
}
