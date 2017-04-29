package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Clients can use this bean to make rentals.
 *
 * @author mcarter
 */
public class Rental {

    private String customer;
    private String film;//TODO this should be a list - assume that due covers all films and is not variable
    private Date due;

    public Rental() {
    }

    public Rental(String customer, String film, Date due) {
        this.customer = customer;
        this.film = film;
        this.due = due;
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
}
