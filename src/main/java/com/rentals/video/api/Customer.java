package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a customer of the rentals service, providing data on points and fines accumulated.
 *
 * @author mcarter
 */
public class Customer {

    private String name;
    private int points;
    private int fines;

    @SuppressWarnings("unused")
    public Customer() {
    }

    public Customer(String name, int points, int fines) {
        this.name = name;
        this.points = points;
        this.fines = fines;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public int getPoints() {
        return points;
    }

    @JsonProperty
    public int getFines() {
        return fines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        if (getPoints() != customer.getPoints())
            return false;
        return getFines() == customer.getFines() && getName().equals(customer.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getPoints();
        result = 31 * result + getFines();
        return result;
    }
}
