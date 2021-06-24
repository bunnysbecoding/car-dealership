package com.sky.carDealership.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public Car(@NonNull String brand, int year, int price, int mileage) {
        this.brand = brand;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
    }
}
