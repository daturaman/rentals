package com.rentals.video.resources;

import com.rentals.video.api.Rental;
import com.rentals.video.db.CustomerDao;
import com.rentals.video.db.FilmDao;
import com.rentals.video.db.RentalDao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * @author mcarter
 */
@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

    private FilmDao filmDao;
    private CustomerDao customerDao;
    private RentalDao rentalDao;

    public RentalResource(FilmDao filmDao, CustomerDao customerDao, RentalDao rentalDao) {
        this.filmDao = filmDao;
        this.customerDao = customerDao;
        this.rentalDao = rentalDao;
    }

    @POST
    public Response rentFilm(@NotNull @Valid Collection<Rental> rentals)
	{
        //Look up film and customer from db
        //Persist the rentals, update customer points and return a receipt with the calculated total/points
        return Response.ok("Matrix 11 (New release) 1 days 40 SEK").build();

    }
}
