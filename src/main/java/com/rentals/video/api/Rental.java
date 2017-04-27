/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mcarter
 */
public class Rental {

	private String film;
	private int days;

	public Rental() {
	}

	public Rental(String film, int days) {
		this.film = film;
		this.days = days;
	}

	@JsonProperty
	public String getFilm() {
		return film;
	}

	@JsonProperty
	public int getDays() {
		return days;
	}
}
