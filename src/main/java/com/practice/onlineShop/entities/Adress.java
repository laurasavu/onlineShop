package com.practice.onlineShop.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Adress {
    public Adress() {
    }

    public Adress(String city, String street, long number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    private String city;
    private String street;
    private long number;

    @Override
    public String toString() {
        return "Adress{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }
}
