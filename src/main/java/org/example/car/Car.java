package org.example.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private CarBrand carBrand;
    private String model;
    private String licensePlate;
    private String color;
    private CarType carType;
    private double carWeight;
}
