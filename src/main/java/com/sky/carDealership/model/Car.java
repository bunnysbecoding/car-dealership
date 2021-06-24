package com.sky.carDealership.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name="cars")
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_gen")
    @SequenceGenerator(name = "car_gen", sequenceName = "car_seq", allocationSize = 1)
    @NonNull
    private Long id;

    @Column
    @NonNull
    private String brand;

    @Column
    @NonNull
    private int year;

    @Column
    @NonNull
    private int price;

    @Column
    @NonNull
    private int mileage;

    @Column
    @NonNull
    @Value("true")
    private boolean available;

    public Car(String brand, int year, int price, int mileage) {
        this.brand = brand;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
        this.available = true;
    }
}
