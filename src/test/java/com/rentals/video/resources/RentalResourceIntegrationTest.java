/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.rentals.video.resources;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.rentals.video.RentalsApplication;
import com.rentals.video.RentalsConfiguration;
import com.rentals.video.api.Rental;
import com.rentals.video.api.RentalResponse;
import com.rentals.video.db.CustomerDao;
import com.rentals.video.db.RentalDao;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

/**
 * Integration test of the rentals application
 *
 * @author mcarter
 */
public class RentalResourceIntegrationTest {
    @Rule
    public final DropwizardAppRule<RentalsConfiguration> RULE =
            new DropwizardAppRule<RentalsConfiguration>(RentalsApplication.class,
                    ResourceHelpers.resourceFilePath("test-config.yml"));
    private Handle handle;
    private CustomerDao customerDao;
    private RentalDao rentalDao;
    private Client client;

    @Before
    public void setUp() throws Exception {
        DBI dbi = new DBI("jdbc:h2:mem:test", "michael", "iAMs00perSecrEET");
        handle = dbi.open();
        customerDao = handle.attach(CustomerDao.class);
        rentalDao = handle.attach(RentalDao.class);
        client = new JerseyClientBuilder().build();
    }

    @After
    public void tearDown() throws Exception {
        handle.close();
    }

    @Test
    public void bobRentsSomeFilms() {
        assertEquals(customerDao.findByName("Bob").getPoints(), 0);
        assertEquals(rentalDao.findDueRentalsForCustomer("Bob").size(), 0);

        Map<String, Integer> films = new HashMap<>();
        films.put("Conan The Librarian", 5);
        films.put("Horror", 2);
        Response resp = client.target("http://localhost:8080/rentals/Bob")
                .request().post(Entity.entity(films, MediaType.APPLICATION_JSON_TYPE));

        RentalResponse complete = resp.readEntity(RentalResponse.class);
        assertEquals(230, complete.getFee());
        assertEquals(customerDao.findByName("Bob").getPoints(), 3);
        assertEquals(rentalDao.findDueRentalsForCustomer("Bob").size(), 2);
    }

    @Test
    public void bobReturnsHisFilmsLateAndGetsFined() throws ParseException {
        Calendar due = Calendar.getInstance();
        due.add(Calendar.DAY_OF_MONTH, -2);
        final List<Rental> rentals = Collections.singletonList(new Rental("Romcom", "Bob", 10, 400, due.getTime(), null));
        rentalDao.insert(rentals);

        Response resp = client.target("http://localhost:8080/rentals/Bob")
                .request().put(Entity.entity(rentals, MediaType.APPLICATION_JSON_TYPE));

        RentalResponse complete = resp.readEntity(RentalResponse.class);
        assertEquals(80, complete.getFee());
        assertEquals(customerDao.findByName("Bob").getFines(), 90);
        assertEquals(rentalDao.findDueRentalsForCustomer("Bob").size(), 0);
    }
}
