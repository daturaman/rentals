package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mcarter
 */
public class Film {

	private Integer id;
	private String title;
	private FilmType type;

	public Film() {
	}

	public Film(Integer id, String title, FilmType type) {
		this.id = id;
		this.title = title;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	@JsonProperty
	public FilmType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Film film = (Film) o;
		if (!getId().equals(film.getId()))
			return false;
		if (!getTitle().equals(film.getTitle()))
			return false;
		return getType() == film.getType();
	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getTitle().hashCode();
		result = 31 * result + getType().hashCode();
		return result;
	}

	public enum FilmType {
		NEW_RELEASE, REGULAR, OLDIE
	}
}
