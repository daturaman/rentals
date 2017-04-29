package com.rentals.video.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mcarter
 */
public class Film {

    private String title;
    private FilmType type;

    public Film() {
    }

    public Film(String title, FilmType type) {
        this.title = title;
        this.type = type;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (!title.equals(film.title)) return false;
        return type == film.type;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    public enum FilmType {
        NEW_RELEASE, REGULAR, OLDIE
    }
}
