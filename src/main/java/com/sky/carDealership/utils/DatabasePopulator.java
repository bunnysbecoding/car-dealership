package com.sky.carDealership.utils;

import com.sky.carDealership.model.Car;
import com.sky.carDealership.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DatabasePopulator {

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    public void databaseInit() {
        List<Car> cars = List.of(
                new Car("Audi",2010,10000,20000),
                new Car("Audi",2012, 10500,35000),
                new Car("BMW",2004,50000,30000),
                new Car("BMW",2020,150000,10000),
                new Car("Mazda",2002,9000,45000),
                new Car("Renault",1995,5000,12000),
                new Car("Fiat",2008,90000,30000)
        ) ;
        carRepository.saveAll(cars);
    }
}
