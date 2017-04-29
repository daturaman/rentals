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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author mcarter
 */
@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
public class RentalResource {

    public static final int PREMIUM_POINTS = 2;
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
    public Order rentFilm(@NotNull @Valid Order order) {
        process(order);
        return order;

    }

    private void process(Order order) {
        Customer customer = customerDao.findByName(order.getCustomer());
        int price = 0;
        int points = customer.getPoints();
        List<Rental> rentals = new ArrayList<>(order.getFilms().size());
        Date due = calculateDueDate(order.getDays());

        for (String title : order.getFilms()) {
            Film film = filmDao.findByTitle(title);
            switch (film.getType()) {
                case NEW_RELEASE: {
                    price += PREMIUM_PRICE * order.getDays();
                    points += PREMIUM_POINTS;
                }
                break;
                case REGULAR: {
                    price += order.getDays() <= 3 ? BASIC_PRICE : BASIC_PRICE + ((order.getDays() - 3) * BASIC_PRICE);
                    points++;
                }
                break;
                case OLDIE: {
                    price += order.getDays() <= 5 ? BASIC_PRICE : BASIC_PRICE + ((order.getDays() - 5) * BASIC_PRICE);
                    points++;
                }
                break;
                default:
                    throw new IllegalArgumentException("Unknown film type");
            }
            rentals.add(new Rental(order.getCustomer(), film.getTitle(), due));
        }

        rentalDao.insert(rentals);
        customerDao.update(points, customer.getName());
        order.setPrice(price);
    }

    private Date calculateDueDate(int days) {
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, days);
        return today.getTime();
    }
}
