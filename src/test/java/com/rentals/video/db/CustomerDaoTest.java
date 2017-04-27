package com.rentals.video.db;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.rentals.video.api.Customer;

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
		customers = Collections.singletonList(new Customer("Bob", 0, 0));
		customerDao.insert(customers);
	}

	@After
	public void tearDown() throws Exception {
		handle.close();
	}

	@Test
	public void canFindCustomerByName(){
		final String expectedName = "Bob";
		CustomerDao customerDao = dbi.onDemand(CustomerDao.class);
		Customer actualFilm = customerDao.findByName(expectedName);
		assertEquals(customers.get(0), actualFilm);
	}
}