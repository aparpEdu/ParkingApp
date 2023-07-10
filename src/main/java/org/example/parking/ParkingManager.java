package org.example.parking;

import org.example.exception.ErrorDetails;
import org.example.exception.UnsupportedCarBrand;
import org.example.exception.UnsupportedCarTypeException;
import org.example.utils.AppConstants;

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
      }catch (UnsupportedCarBrand unsupportedCarBrand) {
            throw new UnsupportedCarBrand (
                ErrorDetails.UNSUPPORTED_CAR_BRAND.getName(), car.getCar().getCarBrand().getName()
            );
        }
        System.out.println(AppConstants.SUCCESSFUL_PARKING);
        return car;
    }


}
