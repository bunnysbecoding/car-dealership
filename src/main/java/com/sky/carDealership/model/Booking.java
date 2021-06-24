package com.sky.carDealership.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="bookings")
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookin_gen")
    @SequenceGenerator(name = "booking_gen", sequenceName = "booking_seq", allocationSize = 1)
    @NonNull
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @NonNull
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NonNull
    private User user;

    @Column
    @NonNull
    private Date timeBooked;

    public Booking(@NonNull Car car, @NonNull User user) {
        this.car = car;
        this.user = user;
        this.timeBooked = new Date();
    }
}
