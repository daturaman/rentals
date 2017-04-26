package com.rentals.video.services;

import org.junit.Before;
import org.junit.Test;

public class FilmServiceTest {

	private FilmService filmService;

	@Before
	public void setUp() throws Exception {
		filmService = new FilmService();
	}

	@Test
	public void canRetrieveFilmFromInventory(){
		filmService.getFilm("");
	}
}