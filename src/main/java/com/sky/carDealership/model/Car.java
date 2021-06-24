package com.sky.carDealership.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="cars")
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    @NonNull
    @Getter @Setter
    private Long id;

    @Column
    @NonNull
    @Getter @Setter
    private String brand;

    @Column
    @NonNull
    @Getter @Setter
    private int year;

    @Column
    @NonNull
    @Getter @Setter
    private int price;

    @Column
    @NonNull
    @Getter @Setter
    private int mileage;

    @Column
    @NonNull
    @Getter @Setter
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
