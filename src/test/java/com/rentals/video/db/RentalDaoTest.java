package com.rentals.video.db;

import com.rentals.video.api.Rental;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RentalDaoTest {
    private Handle handle;
    private List<Rental> rentals;
    private DBI dbi;
    private RentalDao rentalDao;

    @Before
    public void setUp() throws Exception {
        dbi = new DBI("jdbc:h2:mem:test");
        handle = dbi.open();
        rentalDao = handle.attach(RentalDao.class);
        rentalDao.createTable();
        rentals = Arrays.asList(new Rental("Ghandi", "Bob", 0, 0, new Date(), null),
                new Rental("Tron", "Bob", 0, 0, new Date(), null),
                new Rental("Gone With The Wind", "Bob", 0, 0, new Date(), null));
        rentalDao.insert(rentals);
    }

    @After
    public void tearDown() throws Exception {
        handle.close();
    }

    @Test
    public void findsAllRentalsForBob() {
        List<Rental> bobsRentals = rentalDao.findDueRentalsForCustomer("Bob");
        assertTrue(bobsRentals.size() == 3);
    }

    @Test
    public void shouldMarkRentalAsReturned() {
        rentalDao.markRentalsAsReturned(rentals);
        assertTrue(rentalDao.findDueRentalsForCustomer("Bob").size() == 0);
    }
}