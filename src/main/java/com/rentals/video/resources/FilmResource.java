package com.rentals.video.resources;

import com.rentals.video.api.Film;
import com.rentals.video.db.FilmDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Provides access to the films inventory
 */
@Path("/films")
@Produces(MediaType.APPLICATION_JSON)
public class FilmResource {

    private FilmDao filmDao;

    public FilmResource(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @GET
    public List<Film> getFilms() {
        return filmDao.findAll();
    }
}
