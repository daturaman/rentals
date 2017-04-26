package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mcarter
 */
public class Film {

	private String title;
	private FilmType filmType;
	private boolean rented;

	public Film() {
	}

	public Film(String title, FilmType filmType) {
		this.title = title;
		this.filmType = filmType;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	@JsonProperty
	public FilmType getFilmType() {
		return filmType;
	}

	public boolean isRented() {
		return rented;
	}

	public enum FilmType {
		NEW_RELEASE, REGULAR, OLDIE
	}
}
