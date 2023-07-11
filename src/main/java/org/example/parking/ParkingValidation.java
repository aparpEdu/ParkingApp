package org.example.parking;

import org.example.car.Car;
import org.example.car.CarBrand;
import org.example.car.CarType;
import org.example.exception.*;
import org.example.utils.AppConstants;

import java.util.List;

public class ParkingValidation {
    public static void validateCarAttributes(Car car){
        if(car.getCarWeight() > AppConstants.MAX_WEIGHT) {
            throw new WeightOverMaximumException(ErrorDetails.UNSUPPORTED_WEIGHT.getName(), AppConstants.MAX_WEIGHT);
        }
        List<CarType> carTypes = List.of(CarType.values());
        if(!carTypes.contains(car.getCarType())){
            throw new UnsupportedCarTypeException(ErrorDetails.UNSUPPORTED_CAR_TYPE.getName(),
                    car.getCarType().getName());
        }
        List<CarBrand> carBrands = List.of(CarBrand.values());
        if(!carBrands.contains(car.getCarBrand())){
            throw new UnsupportedCarBrandException(
                    ErrorDetails.UNSUPPORTED_CAR_BRAND.getName(), car.getCarBrand().getName()
            );
        }
    }

    public static void validateParkingFee(ParkedCar parkedCar, double paidFee){
        if(paidFee < parkedCar.getFee()){
            throw new InvalidSumException(ErrorDetails.INVALID_SUM.getName(), parkedCar.getFee());
        }
    }

    public static void validateTimeExtension(int hours){
        if(hours < 1){
            throw new InvalidExtensionHours(ErrorDetails.UNSUPPORTED_HOURS.getName());
        }
    }
}
