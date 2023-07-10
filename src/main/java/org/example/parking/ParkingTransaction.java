package org.example.parking;


import org.example.utils.AppConstants;

public class ParkingTransaction {
    public static ParkedCar payFee(ParkedCar parkedCar, double parkingFee){
        ParkingValidation.validateParkingFee(parkedCar, parkingFee);
        parkedCar.setPaidFee(true);
        System.out.println(AppConstants.SUCCESSFUL_FEE_PAYMENT);
        return parkedCar;
    }

    public static ParkedCar extendParkingDueTime(int hours, ParkedCar parkedCar){
        ParkingValidation.validateTimeExtension(hours);
        parkedCar.setPaidFee(false);
        parkedCar.setDueTime(parkedCar.getDueTime().plusHours(hours));
        double totalFee = parkedCar.getFee() * hours;
        parkedCar.setFee(totalFee);
        System.out.println(AppConstants.SUCCESSFUL_EXTENSION);
        return parkedCar;
    }
}
