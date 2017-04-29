/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.rentals.video.resources;

import com.rentals.video.RentalsApplication;
import com.rentals.video.RentalsConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * @author mcarter
 */
public class RentalResourceEndpointTest {
    @Rule
    public final DropwizardAppRule<RentalsConfiguration> RULE =
            new DropwizardAppRule<RentalsConfiguration>(RentalsApplication.class,
                    ResourceHelpers.resourceFilePath("test-config.yml"));

    @Before
    public void setUp() throws Exception {
        //Db set up
    }

    @Test
    public void canRentOneFilmAndCalculateTotal() {
        Client client = new JerseyClientBuilder().build();
        Response resp = client.target("/rentals")
                .request().post(Entity.entity(Collections.singletonList(null), MediaType.APPLICATION_JSON_TYPE));
        System.out.println(resp);
//		String resp = resources.client().target("/rentals")
//							   .request().get(String.class);
//		System.out.println(resp);
        //assert order contains price, points, due date
        //assert rental record created with due date
        //assert customer record updated
    }
}
