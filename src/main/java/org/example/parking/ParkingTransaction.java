package org.example.parking;



public class ParkingTransaction {
    public static ParkedCar payFee(ParkedCar parkedCar, double parkingFee){
        ParkingValidation.validateParkingFee(parkedCar, parkingFee);
        parkedCar.setPaidFee(true);
        System.out.println("Successfully paid the parking fee");
        return parkedCar;
    }

    public static ParkedCar extendParkingDueTime(int hours, ParkedCar parkedCar){
        ParkingValidation.validateTimeExtension(hours);
        parkedCar.setPaidFee(false);
        parkedCar.setDueTime(parkedCar.getDueTime().plusHours(hours));
        double totalFee = parkedCar.getFee() * hours;
        parkedCar.setFee(totalFee);
        System.out.println("Successfully extended the parking due time ");
        return parkedCar;
    }
}
