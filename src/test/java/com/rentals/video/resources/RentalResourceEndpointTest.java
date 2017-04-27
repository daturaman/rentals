/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.rentals.video.resources;

import java.util.Collections;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import com.rentals.video.api.Film;
import com.rentals.video.api.Rental;

import io.dropwizard.testing.junit.ResourceTestRule;

/**
 * @author mcarter
 */
public class RentalResourceEndpointTest {
	@Rule
	public final ResourceTestRule resources = ResourceTestRule
			.builder()
			.addResource(new RentalResource())
			.build();

	@Test
	public void canRentOneFilmAndCalculateTotal(){
		Film predator = new Film(1, "Predator", Film.FilmType.OLDIE);
		Rental rental = new Rental("predator", 3);
		Response resp = resources.client().target("/rentals")
								 .request().post(Entity.entity(Collections.singletonList(rental), MediaType.APPLICATION_JSON_TYPE));
		System.out.println(resp);
//		String resp = resources.client().target("/rentals")
//							   .request().get(String.class);
//		System.out.println(resp);
	}
}
