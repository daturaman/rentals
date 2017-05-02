package com.rentals.video.resources;

import com.rentals.video.api.Customer;
import com.rentals.video.db.CustomerDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Provides access to Customer information
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private CustomerDao customerDao;

    public CustomerResource(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @GET
    @Path("/{name}")
    public Customer getCustomer(@PathParam("name") String name) {
        return customerDao.findByName(name);
    }
}
