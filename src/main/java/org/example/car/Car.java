package org.example.car;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Car {
    private CarBrand carBrand;
    private String model;
    private String licensePlate;
    private String color;
    private CarType carType;
    private double carWeight;
}
