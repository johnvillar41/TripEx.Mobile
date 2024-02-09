package com.tripex.tripexmobile.Views.Interfaces;

import com.tripex.tripexmobile.Models.Vehicle;

import java.util.List;

public interface IVehicleView extends IBaseView{
    void displayVehicle(Vehicle vehicle);

    void initializeScanner();
}
