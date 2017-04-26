package com.rentals.video.resources;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rentals.video.api.Rental;

/**
 * @author mcarter
 */
@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

	@POST
	public Rental rentFilm(@NotNull @Valid Collection<Rental> rentals)
	{
		//Persist the rentals, update customer and return a receipt with the calculated total
		return null;
	}
}
