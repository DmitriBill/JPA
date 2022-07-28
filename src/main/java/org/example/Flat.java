package org.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flat {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String region;
    private String address;
    private double square;
    private int rooms;
    private int price;

    public Flat () {}

    public Flat(String region, String address, double square, int rooms, int price) {
        this.id = id;
        this.region = region;
        this.address = address;
        this.square = square;
        this.rooms = rooms;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getAddress() {
        return address;
    }

    public double getSquare() {
        return square;
    }

    public int getRooms() {
        return rooms;
    }

    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat {" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", square=" + square +
                ", rooms=" + rooms +
                ", price=" + price +
                '}';
    }
}
