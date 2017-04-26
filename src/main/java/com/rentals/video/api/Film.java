package com.rentals.video.api;

/**
 * @author mcarter
 */
public class Film {

	private String title;
	private FilmType filmType;

	public String getTitle() {
		return title;
	}

	public FilmType getFilmType() {
		return filmType;
	}

	public enum FilmType {
		NEW_RELEASE, REGULAR, OLDIE
	}
}
