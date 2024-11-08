package br.com.locatech.locatech.entities;

import lombok.*;

import java.math.BigDecimal;

//Annotations from Lombok.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vehicle {
    private Long id;
    private String brand;
    private String model;
    private String plate;
    private int vehicleYear;
    private String color;
    private BigDecimal dailyPrice;
}
