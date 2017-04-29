package com.rentals.video.db;

import com.rentals.video.api.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerDaoTest {
	private Handle handle;
	private List<Customer> customers;
	private DBI dbi;

	@Before
	public void setUp() throws Exception {
		dbi = new DBI("jdbc:h2:mem:test");
		handle = dbi.open();
		CustomerDao customerDao = handle.attach(CustomerDao.class);
		customerDao.createTable();
        customers = Collections.singletonList(new Customer("Bob", 250, 10));
        customerDao.insert(customers);
	}

	@After
	public void tearDown() throws Exception {
		handle.close();
	}

	@Test
	public void canFindCustomerByName(){
		CustomerDao customerDao = dbi.onDemand(CustomerDao.class);
        Customer bob = customerDao.findByName("Bob");
        assertEquals(customers.get(0), bob);
    }
}