package com.tripex.tripexmobile.Services.Implementations;

import com.tripex.tripexmobile.Services.BaseService;
import com.tripex.tripexmobile.Services.Interfaces.IQrScannerService;

public class QrScannerService extends BaseService implements IQrScannerService {
    private static QrScannerService singleInstance = null;

    public static synchronized QrScannerService getInstance() {
        if (singleInstance == null)
            singleInstance = new QrScannerService();

        return singleInstance;
    }

    private QrScannerService() {

    }
}
