package org.example.parking;

import org.example.car.Car;
import org.example.car.CarBrand;
import org.example.car.CarType;
import org.example.exception.ErrorDetails;
import org.example.exception.InvalidExtensionHours;
import org.example.exception.InvalidSumException;
import org.example.exception.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTransactionTest {

    private Car carToPark;
    private Set<ParkedCar> parkedCars;

    @BeforeEach
    void setUp() {
        this.carToPark = new Car();
        carToPark.setCarBrand(CarBrand.BMW);
        carToPark.setCarWeight(1200);
        carToPark.setCarType(CarType.COUPE);
        carToPark.setModel("M3");
        carToPark.setLicensePlate("B 7777 BB");
        carToPark.setColor("BLUE");
        parkedCars = Parking.getInstance().getParkedCars();
        ParkingManager.addCarToParkingRegister(carToPark, 2);
    }

    @Test
    void feeShouldBePaidWhenProvidedCorrectFeeSum() {
       ParkedCar parkedCar = parkedCars.stream()
                .filter(car -> car.getCar().getLicensePlate().equalsIgnoreCase("B 7777 BB"))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("Car", "License Plate", "B 7777 BB"));
        boolean feeStatusBeforePayment = parkedCar.isPaidFee();
        double correctFeePayment = 40;
        ParkingTransaction.payFee(parkedCar, correctFeePayment);
        boolean feeStatusAfterPayment = parkedCar.isPaidFee();
        assertNotEquals(feeStatusBeforePayment, feeStatusAfterPayment);
    }

    @Test
    void feeShouldNotBePaidWhenProvidedIncorrectFeeSum() {
        ParkedCar parkedCar = parkedCars.stream()
                .filter(car -> car.getCar().getLicensePlate().equalsIgnoreCase("B 7777 BB"))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("Car", "License Plate", "B 7777 BB"));
        double incorrectFeePayment = 30;
        InvalidSumException thrown = assertThrows(
                InvalidSumException.class,
                () -> ParkingTransaction.payFee(parkedCar, incorrectFeePayment),
                ErrorDetails.UNSUPPORTED_CAR_TYPE.getName()
        );
        assertTrue(thrown.getMessage().contains("Sum you provided is less"));
    }

    @Test
    void parkingDueTimeShouldBeExtendedWhenProvidedCorrectHours(){
        ParkedCar parkedCar = parkedCars.stream()
                .filter(car -> car.getCar().getLicensePlate().equalsIgnoreCase("B 7777 BB"))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("Car", "License Plate", "B 7777 BB"));
        int correctHours = 2;
        double feeBeforeExtension = parkedCar.getFee();
        LocalDateTime timeBeforeExtensions = parkedCar.getDueTime();
        ParkingTransaction.extendParkingDueTime(correctHours, parkedCar);
        double feeAfterExtension = parkedCar.getFee();
        LocalDateTime timeAfterExtensions = parkedCar.getDueTime();
        assertTrue(feeAfterExtension > feeBeforeExtension);
        assertTrue(timeBeforeExtensions.isBefore(timeAfterExtensions));
    }

    @Test
    void parkingDueTimeShouldNotBeExtendedWhenProvidedIncorrectHours(){
        ParkedCar parkedCar = parkedCars.stream()
                .filter(car -> car.getCar().getLicensePlate().equalsIgnoreCase("B 7777 BB"))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("Car", "License Plate", "B 7777 BB"));
        int incorrectHours = -2;
        InvalidExtensionHours thrown = assertThrows(
                InvalidExtensionHours.class,
                () -> ParkingTransaction.extendParkingDueTime(incorrectHours, parkedCar),
                ErrorDetails.UNSUPPORTED_HOURS.getName()
        );
        assertTrue(thrown.getMessage().contains("Hours you provided are not supported"));
    }
    @AfterEach
    void afterEach() {
        ParkingManager.removeCarFromParkingRegister(carToPark.getLicensePlate());
    }
}