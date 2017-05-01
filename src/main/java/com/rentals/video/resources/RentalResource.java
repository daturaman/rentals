package com.rentals.video.resources;

import com.rentals.video.api.Customer;
import com.rentals.video.api.Film;
import com.rentals.video.api.Order;
import com.rentals.video.api.Rental;
import com.rentals.video.db.CustomerDao;
import com.rentals.video.db.FilmDao;
import com.rentals.video.db.RentalDao;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * RESTful representation of a video rental, which consists of one or more films to be rented for n days.
 *
 * @author mcarter
 */
@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

    public static final int PREMIUM_POINTS = 2;
    public static final int REGULAR_DAYS = 3;
    public static final int OLDIE_DAYS = 5;
    private FilmDao filmDao;
    private CustomerDao customerDao;
    private RentalDao rentalDao;
    private static final int PREMIUM_PRICE = 40;
    private static final int BASIC_PRICE = 30;

    public RentalResource(FilmDao filmDao, CustomerDao customerDao, RentalDao rentalDao) {
        this.filmDao = filmDao;
        this.customerDao = customerDao;
        this.rentalDao = rentalDao;
    }

    @POST
    @Path("/{customer}")
    public Order rentFilms(@PathParam("customer") String customer, @NotNull @Valid Map<String, Integer> filmsToRent) {
        return process(customer, filmsToRent);
    }

    //TODO return a film
    @PUT
    public Response returnFilms() {
        //Delete each film from rental; calculate fines and add to customer
        return null;
    }

    private Order process(String customerName, Map<String, Integer> filmsToRent) {
        Customer customer = customerDao.findByName(customerName);
        int totalPrice = 0;
        int points = customer.getPoints();
        List<Rental> rentals = new ArrayList<>(filmsToRent.size());

        for (Map.Entry<String, Integer> filmToRent : filmsToRent.entrySet()) {
            Film film = filmDao.findByTitle(filmToRent.getKey());
            int days = filmToRent.getValue();
            int rentalPrice = 0;
            switch (film.getType()) {
                case NEW_RELEASE: {
                    rentalPrice = PREMIUM_PRICE * days;
                    points += PREMIUM_POINTS;
                }
                break;
                case REGULAR: {
                    rentalPrice = days <= REGULAR_DAYS ? BASIC_PRICE : BASIC_PRICE + ((days - REGULAR_DAYS) * BASIC_PRICE);
                    points++;
                }
                break;
                case OLDIE: {
                    rentalPrice = days <= OLDIE_DAYS ? BASIC_PRICE : BASIC_PRICE + ((days - OLDIE_DAYS) * BASIC_PRICE);
                    points++;
                }
                break;
                default:
                    throw new IllegalArgumentException("Unknown film type");
            }
            rentals.add(new Rental(film.getTitle(), customer.getName(), days, rentalPrice, calculateDueDate(days), null));
            totalPrice += rentalPrice;
        }

        rentalDao.insert(rentals);
        customerDao.update(points, customer.getName());

        return new Order(customerName, rentals, totalPrice);
    }

    private Date calculateDueDate(int days) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, days);
        return today.getTime();
    }
}
