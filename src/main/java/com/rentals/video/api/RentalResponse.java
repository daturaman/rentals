package com.rentals.video.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a completed order for rentals/returns, detailing each individual {@link Rental} and a total fee/fine.
 *
 * @author mcarter
 */
public class RentalResponse {

    private String customer;
    private List<Rental> items;
    private int fee;
    private int points;

    public RentalResponse() {
    }

    public RentalResponse(String customer, List<Rental> items, int fee, int points) {
        this.customer = customer;
        this.items = items;
        this.fee = fee;
        this.points = points;
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
    public int getFee() {
        return fee;
    }

    @JsonProperty
    public int getPoints() {
        return points;
    }
}
