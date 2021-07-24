package models;

import lombok.*;

@Setter//это сетеры (от lombok) на все поля, чтобы не писать в класаа все поля сеторов и геторов
@Getter
@Builder//будет помогать строить объект этого класса
@AllArgsConstructor//конструктор со всеми полями
@NoArgsConstructor//конструктор без аргументов
@ToString//чтобы могли распечатать объект
public class Car {

    private String address;
    private String make;
    private String model;
    private String year;
    private String engine;
    private String fuel;
    private String gear;
    private String wD;
    private String doors;
    private String seats;
    private String clasS;
    private String fuelConsumption;
    private String carRegNumber;
    private String price;
    private String distanceIncluded;
    private String typeFeature;
    private String about;

}
