package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a completed order for rentals, detailing each individual {@link Rental} and a total price.
 *
 * @author mcarter
 */
public class Order {

    private String customer;
    private List<Rental> items;
    private int price;

    public Order() {
    }

    public Order(String customer, List<Rental> items, int price) {
        this.customer = customer;
        this.items = items;
        this.price = price;
    }

    @JsonProperty
    public String getCustomer() {
        return customer;
    }

    @JsonProperty
    public List<Rental> getItems() {
        return items;
    }

    @JsonProperty
    public int getPrice() {
        return price;
    }
}
