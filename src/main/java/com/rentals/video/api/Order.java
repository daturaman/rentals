package com.rentals.video.api;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mcarter
 */
public class Order {

	int total;
	Collection<Rental> rentals;

	public Order() {
	}

	public Order(int total, Collection<Rental> rentals) {
		this.total = total;
		this.rentals = rentals;
	}

	@JsonProperty
	public int getTotal() {
		return total;
	}

	@JsonProperty
	public Collection<Rental> getRentals() {
		return rentals;
	}
}
