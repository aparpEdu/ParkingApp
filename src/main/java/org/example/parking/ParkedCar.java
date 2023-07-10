package org.example.parking;

import lombok.Getter;
import lombok.Setter;
import org.example.car.Car;


import java.time.LocalDateTime;

@Getter
@Setter
public class ParkedCar {
    private Car car;
    private double fee;
    private boolean paidFee;
    private LocalDateTime dueTime;
}
