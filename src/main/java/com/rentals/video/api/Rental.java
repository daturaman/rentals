/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.rentals.video.api;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mcarter
 */
public class Rental {
	//A rental has a one to one relationship with a film
	//TODO Film could just be a string name
	private Film film;//New/regular/old
	private int days;
	private Date dueDate;

	public Rental() {
	}

	public Rental(Film film, int days) {
		this.film = film;
		this.days = days;
	}

	@JsonProperty
	public Film getFilm() {
		return film;
	}

	@JsonProperty
	public int getDays() {
		return days;
	}

	public Date getDueDate() {
		return dueDate;
	}
}
