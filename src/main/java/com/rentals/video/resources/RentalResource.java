package com.rentals.video.resources;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rentals.video.api.Customer;
import com.rentals.video.api.Film;
import com.rentals.video.api.Rental;
import com.rentals.video.api.RentalResponse;
import com.rentals.video.db.CustomerDao;
import com.rentals.video.db.FilmDao;
import com.rentals.video.db.RentalDao;

/**
 * RESTful representation of a video rental, which consists of one or more films to be rented for n days.
 *
 * @author mcarter
 */
@Path("/rentals/{customer}")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

    public static final int PREMIUM_POINTS = 2;
    public static final int REGULAR_DAYS = 3;
    public static final int OLDIE_DAYS = 5;
    private static final int REGULAR_POINTS = 1;
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
    public RentalResponse rentFilms(@PathParam("customer") String customer, @NotNull @Valid Map<String, Integer> filmsToRent) {
        return process(customer, filmsToRent);
    }

    @PUT
    public RentalResponse returnFilms(@PathParam("customer") String customerName, @NotNull @Valid List<Rental> rentals) {
        Customer customer = customerDao.findByName(customerName);
        int fines = 0;
        for (Rental rental : rentals) {
            Film film = filmDao.findByTitle(rental.getFilm());
            long daysOverdue = getDaysOverdue(rental.getDue(), new Date());
            if(daysOverdue > 0){
                fines += calculateFee(film, (int) daysOverdue);
            }
        }
        customerDao.updateFines(customer.getFines() + fines, customerName);
        rentalDao.markRentalsAsReturned(rentals);
        return new RentalResponse(customerName, rentals, fines, customer.getPoints());
    }

    private RentalResponse process(String customerName, Map<String, Integer> filmsToRent) {
        Customer customer = customerDao.findByName(customerName);
        int totalPrice = 0;
        int points = 0;
        List<Rental> rentals = new ArrayList<>(filmsToRent.size());

        for (Map.Entry<String, Integer> filmToRent : filmsToRent.entrySet()) {
            Film film = filmDao.findByTitle(filmToRent.getKey());
            int days = filmToRent.getValue();
            int rentalPrice = calculateFee(film, days);
            points += film.getType() == Film.FilmType.NEW_RELEASE ? PREMIUM_POINTS : REGULAR_POINTS;
            rentals.add(new Rental(film.getTitle(), customer.getName(), days, rentalPrice, calculateDueDate(days), null));
            totalPrice += rentalPrice;
        }

        rentalDao.insert(rentals);
        customerDao.updatePoints(customer.getPoints() + points, customer.getName());

        return new RentalResponse(customerName, rentals, totalPrice, points);
    }

    private int calculateFee(Film film, int days) {
        int rentalPrice;
        switch (film.getType()) {
            case NEW_RELEASE: {
                rentalPrice = PREMIUM_PRICE * days;
            }
            break;
            case REGULAR: {
                rentalPrice = days <= REGULAR_DAYS ? BASIC_PRICE : BASIC_PRICE + ((days - REGULAR_DAYS) * BASIC_PRICE);
            }
            break;
            case OLDIE: {
                rentalPrice = days <= OLDIE_DAYS ? BASIC_PRICE : BASIC_PRICE + ((days - OLDIE_DAYS) * BASIC_PRICE);
            }
            break;
            default:
                throw new IllegalArgumentException("Unknown film type");
        }
        return rentalPrice;
    }

    private Date calculateDueDate(int days) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, days);
        return today.getTime();
    }

    private long getDaysOverdue(Date due, Date returned) {
        long diff = returned.getTime() - due.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
