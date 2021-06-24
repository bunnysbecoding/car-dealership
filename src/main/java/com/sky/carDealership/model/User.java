package com.sky.carDealership.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @NonNull
    @Getter @Setter
    private Long id;

    @Column
    @NonNull
    @Getter @Setter
    private String name;

    @Column
    @NonNull
    @Getter @Setter
    private String surname;

    @Column
    @NonNull
    @Getter @Setter
    private String email;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
