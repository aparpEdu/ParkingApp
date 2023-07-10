package org.example.parking;

import org.example.car.Car;
import org.example.exception.ErrorDetails;
import org.example.exception.InvalidExtensionHours;
import org.example.exception.InvalidSumException;
import org.example.exception.WeightOverMaximumException;
import org.example.utils.AppConstants;

public class ParkingValidation {
    public static void validateCarAttributes(Car car){
        if(car.getCarWeight() > AppConstants.MAX_WEIGHT) {
            throw new WeightOverMaximumException(ErrorDetails.UNSUPPORTED_WEIGHT.getName(), AppConstants.MAX_WEIGHT);
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
