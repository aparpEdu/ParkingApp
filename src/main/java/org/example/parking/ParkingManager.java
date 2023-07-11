package org.example.parking;

import org.example.car.Car;
import org.example.exception.ResourceNotFoundException;
import org.example.utils.AppConstants;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class ParkingManager {
    public static ParkedCar addCarToParkingRegister(Car car, int hoursParked) {
        ParkingValidation.validateCarAttributes(car);
        ParkingValidation.validateTimeExtension(hoursParked);
        ParkedCar carToPark = new ParkedCar();
        carToPark.setCar(car);
        carToPark.setFee(AppConstants.STANDARD_FEE * hoursParked);
        carToPark.setDueTime(LocalDateTime.now().plusHours(hoursParked));
        Parking.getInstance().getParkedCars().add(carToPark);
        System.out.println(AppConstants.SUCCESSFUL_PARKING);
        return carToPark;
    }

    public static void removeCarFromParkingRegister(String licensePlate){
        Set <ParkedCar> parkedCars = Parking.getInstance().getParkedCars();
        Optional<ParkedCar> carToRemove = parkedCars.stream()
                .filter(parkedCar -> parkedCar.getCar().getLicensePlate().equalsIgnoreCase(licensePlate))
                .findAny();
        parkedCars.remove(carToRemove
                .orElseThrow(() -> new ResourceNotFoundException("Car", "License Plate", licensePlate)));
        Parking.getInstance().setParkedCars(parkedCars);
        System.out.println(AppConstants.SUCCESSFUL_REMOVAL);
    }


}
