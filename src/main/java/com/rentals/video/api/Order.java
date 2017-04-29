package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Representation of a request to rent one or more films.
 *
 * @author mcarter
 */
public class Order {

    private String customer;
    private List<String> films;
    private int days;
    private int price;

    public Order() {
    }

    public Order(String customer, List<String> films, int days) {
        this.customer = customer;
        this.films = films;
        this.days = days;
    }

    @JsonProperty
    public String getCustomer() {
        return customer;
    }

    @JsonProperty
    public List<String> getFilms() {
        return films;
    }

    @JsonProperty
    public int getDays() {
        return days;
    }

    @JsonProperty
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
