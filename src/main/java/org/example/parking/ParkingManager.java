package org.example.parking;

import org.example.exception.ErrorDetails;
import org.example.exception.ResourceNotFoundException;
import org.example.exception.UnsupportedCarBrandException;
import org.example.exception.UnsupportedCarTypeException;
import org.example.utils.AppConstants;
import java.util.Optional;
import java.util.Set;

public class ParkingManager {
    public ParkedCar addCarToParkingRegister(ParkedCar car){
        ParkingValidation.validateCarAttributes(car.getCar());
        try {
            Parking.getInstance().getParkedCars().add(car);
        }
      catch (UnsupportedCarTypeException exception) {
            throw new UnsupportedCarTypeException (
                            ErrorDetails.UNSUPPORTED_CAR_TYPE.getName(),
                            car.getCar().getCarType().getName()
                    );
      }catch (UnsupportedCarBrandException unsupportedCarBrandException) {
            throw new UnsupportedCarBrandException(
                ErrorDetails.UNSUPPORTED_CAR_BRAND.getName(), car.getCar().getCarBrand().getName()
            );
        }
        System.out.println(AppConstants.SUCCESSFUL_PARKING);
        return car;
    }

    public void removeCarFromParkingRegister(String licensePlate){
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
