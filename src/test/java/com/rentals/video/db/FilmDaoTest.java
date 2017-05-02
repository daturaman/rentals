package com.rentals.video.db;

import com.rentals.video.api.Film;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.Arrays;
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
        filmList = Arrays.asList(new Film("Predator", Film.FilmType.OLDIE), new Film("Scanners", Film.FilmType.OLDIE));
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

    @Test
    public void canRetrieveAllFilms() {
        FilmDao filmDao = dbi.onDemand(FilmDao.class);
        assertEquals(filmDao.findAll().size(), filmList.size());
    }
}