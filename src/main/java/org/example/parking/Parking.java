package org.example.parking;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Parking {
    private static Parking instance = null;
    @Setter
    @Getter
    private Set<ParkedCar> parkedCars = new HashSet<>();
    private Parking(){}
    public static Parking getInstance() {
        if(instance == null) {
            instance = new Parking();
        }
        return instance;
    }
}
