package org.example.car;

public enum CarType {
    SUV("SUV"),
    COUPE("Coupe"),
    SEDAN("Sedan"),
    MINI_BUS("Mini-Bus");

    private final String name;

    CarType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
