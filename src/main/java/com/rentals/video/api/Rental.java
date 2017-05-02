package com.rentals.video.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The actual resource published by the Rental api.
 *
 * @author mcarter
 */
public class Rental {

    private String customer;
    private String film;
    private Date due;
    private Date returned;
    private int days;
    private int price;

    public Rental() {
    }

    public Rental(String film, String customer, int days, int price, Date due, Date returned) {
        this.customer = customer;
        this.film = film;
        this.due = due;
        this.days = days;
        this.price = price;
        this.returned = returned;
    }

    @JsonProperty
    public String getCustomer() {
        return customer;
    }

    @JsonProperty
    public String getFilm() {
        return film;
    }

    @JsonProperty
    public Date getDue() {
        return due;
    }

    @JsonProperty
    public Date getReturned() {
        return returned;
    }

    @JsonProperty
    public int getDays() {
        return days;
    }

    @JsonProperty
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %d days %d SEK", film, days, price);
    }
}
