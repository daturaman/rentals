package com.rentals.video.services;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.rentals.video.api.Film;

/**
 * Service primarily for accessing the inventory of films for rent.
 * 
 * @author mcarter
 */
public class FilmService {

	public Film getFilm(String title) {
		JsonReader jsonReader = new JsonReader(
				new InputStreamReader(FilmService.class.getResourceAsStream("/films.json")));
		Type collectionType = new TypeToken<List<Film>>() {
		}.getType();
		Gson gson = new Gson();
		List<Film> films = gson.fromJson(jsonReader, collectionType);
		return films.get(0);
	}

	public static void main(String[] args) {
		final Film film = new FilmService().getFilm("");
		System.out.println(film.getTitle());
	}
}
