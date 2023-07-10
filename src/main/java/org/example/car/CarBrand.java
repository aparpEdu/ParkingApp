package org.example.car;



public enum CarBrand {
    BMW("Bmw"),
    MERCEDES("Mercedes"),
    TOYOTA("Toyota"),
    CITROEN("Citroen"),
    MAZDA("Mazda"),
    HONDA("Honda"),
    AUDI("Audi"),
    NISSAN("Nissan"),
    FORD("Ford"),
    CHEVROLET("Chevrolet"),
    VOLKSWAGEN("Volkswagen"),
    HYUNDAI("Hyundai"),
    KIA("Kia"),
    SUBARU("Subaru"),
    JEEP("Jeep"),
    LEXUS("Lexus"),
    VOLVO("Volvo"),
    PEUGEOT("Peugeot"),
    FIAT("Fiat"),
    LAND_ROVER("Land Rover");

    private final String name;

    CarBrand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
