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
import com.rentals.video.api.Order;
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
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
        Response resp = client.target("http://localhost:8080/rentals")
                .request().post(Entity.entity(new Order("Bob", Arrays.asList("Conan The Librarian"), 5), MediaType.APPLICATION_JSON_TYPE));

        Order complete = (Order) resp.readEntity(Order.class);
        System.out.println(complete.getPrice());
        assertEquals(200, complete.getPrice());
        //assert order contains price, points, due date
        //assert rental record created with due date
        //assert customer record updated
    }
}
