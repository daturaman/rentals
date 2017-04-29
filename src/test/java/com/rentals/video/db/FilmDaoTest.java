package com.rentals.video.db;

import com.rentals.video.api.Film;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilmDaoTest {

	private Handle handle;
	private List<Film> filmList;
	private DBI dbi;

	@Before
	public void setUp() throws Exception {
		dbi = new DBI("jdbc:h2:mem:test");
		handle = dbi.open();
		FilmDao filmDao = handle.attach(FilmDao.class);
		filmDao.createTable();
        filmList = Collections.singletonList(new Film("Predator", Film.FilmType.OLDIE));
        filmDao.insert(filmList);
	}

	@After
	public void tearDown() throws Exception {
		handle.close();
	}

	@Test
	public void canRetrieveFilmFromInventory(){
		final String expectedTitle = "Predator";
		FilmDao filmDao = dbi.onDemand(FilmDao.class);
		Film actualFilm = filmDao.findByTitle(expectedTitle);
		assertEquals(filmList.get(0), actualFilm);
	}
}