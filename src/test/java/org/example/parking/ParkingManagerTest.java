package org.example.parking;

import org.example.car.Car;
import org.example.car.CarBrand;
import org.example.car.CarType;
import org.example.exception.ErrorDetails;
import org.example.exception.InvalidExtensionHours;
import org.example.exception.UnsupportedCarTypeException;
import org.example.exception.WeightOverMaximumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {
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
    }

    @Test
    void carShouldBeAddedToParkingRegisterWhenProvidedCorrectAttributes() {
        int correctHours = 3;
        int initialNumberOfParkedCars = parkedCars.size();
        ParkingManager.addCarToParkingRegister(carToPark,correctHours);
        int expectedNumberOfParkedCars = parkedCars.size();
        assertNotEquals(initialNumberOfParkedCars, expectedNumberOfParkedCars);
    }

    @Test
    void carShouldNotBeAddedToParkingRegisterWhenProvidedIncorrectHours() {
        int inCorrectHours = 0;
        InvalidExtensionHours thrown = assertThrows(
                InvalidExtensionHours.class,
                () -> ParkingManager.addCarToParkingRegister(carToPark, inCorrectHours),
                ErrorDetails.UNSUPPORTED_HOURS.getName()
        );
        assertTrue(thrown.getMessage().contains("Hours you provided are not supported"));
    }

    @Test
    void carShouldNotBeAddedToParkingRegisterWhenProvidedIncorrectCarType() {
        String incorrectCarType = "UFO";
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> carToPark.setCarType(CarType.valueOf(incorrectCarType)),
                ErrorDetails.UNSUPPORTED_CAR_TYPE.getName()
        );
        assertTrue(thrown.getMessage().contains("UFO"));
    }
    @Test
    void carShouldNotBeAddedToParkingRegisterWhenProvidedIncorrectCarBrand() {
        String incorrectCarBrand = "Maclaren";
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> carToPark.setCarBrand(CarBrand.valueOf(incorrectCarBrand)),
                ErrorDetails.UNSUPPORTED_CAR_TYPE.getName()
        );
        assertTrue(thrown.getMessage().contains("Maclaren"));
    }

    @Test
    void carShouldNotBeAddedToParkingRegisterWhenProvidedIncorrectWeight() {
        int inCorrectWeight = 30000;
        int correctHours = 3;
        carToPark.setCarWeight(inCorrectWeight);
        WeightOverMaximumException thrown = assertThrows(
                WeightOverMaximumException.class,
                () -> ParkingManager.addCarToParkingRegister(carToPark, correctHours),
                ErrorDetails.UNSUPPORTED_WEIGHT.getName()
        );
        assertTrue(thrown.getMessage().contains("Weight"));
    }
    @Test
    void carShouldHaveCorrectParkingFeeWhenAddedToParkingRegister() {
        int correctHours = 3;
        double expectedCarFee = 60;
        double carFee = ParkingManager.addCarToParkingRegister(carToPark, correctHours).getFee();
        assertEquals(expectedCarFee, carFee);
    }
    @Test
    void carShouldHaveCorrectDueTimeWhenAddedToParkingRegister() {
        int correctHours = 3;
        int expectedDueTimeHours = LocalDateTime.now().plusHours(correctHours).getHour();
        int dueTimeHours = ParkingManager.addCarToParkingRegister(carToPark, correctHours).getDueTime().getHour();
        assertEquals(expectedDueTimeHours, dueTimeHours);
    }

    @Test
    void removeCarFromParkingRegister() {
    }
}