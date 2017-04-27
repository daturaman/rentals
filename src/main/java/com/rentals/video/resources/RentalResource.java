package com.rentals.video.resources;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rentals.video.api.Rental;

/**
 * @author mcarter
 */
@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

	@POST
	public Response rentFilm(@NotNull @Valid Collection<Rental> rentals)
	{
		//rentals.forEach(rental -> System.out.println(rental.getFilm().getTitle()));
		//Persist the rentals, update customer and return a receipt with the calculated total
		return Response.ok("Matrix 11 (New release) 1 days 40 SEK").build();
	}
}
