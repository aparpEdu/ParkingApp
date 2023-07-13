package org.example.parking;


import org.example.utils.AppConstants;

public class ParkingTransaction {
    public static void payFee(ParkedCar parkedCar, double parkingFee){
        ParkingValidation.validateParkingFee(parkedCar, parkingFee);
        parkedCar.setPaidFee(true);
        System.out.println(AppConstants.SUCCESSFUL_FEE_PAYMENT);
    }

    public static void extendParkingDueTime(int hours, ParkedCar parkedCar){
        ParkingValidation.validateTimeExtension(hours);
        parkedCar.setPaidFee(false);
        parkedCar.setDueTime(parkedCar.getDueTime().plusHours(hours));
        double totalFee = parkedCar.getFee();
        for(int i = 1; i<= hours; i++){
            totalFee += 20;
        }
        parkedCar.setFee(totalFee);
        System.out.println(AppConstants.SUCCESSFUL_EXTENSION);
    }
}
