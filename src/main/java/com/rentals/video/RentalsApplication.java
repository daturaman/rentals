package com.rentals.video;

import com.rentals.video.api.Customer;
import com.rentals.video.api.Film;
import com.rentals.video.db.CustomerDao;
import com.rentals.video.db.FilmDao;
import com.rentals.video.db.RentalDao;
import com.rentals.video.resources.CustomerResource;
import com.rentals.video.resources.FilmResource;
import com.rentals.video.resources.RentalResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Arrays;

public class RentalsApplication extends Application<RentalsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RentalsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Rentals";
    }

    @Override
    public void initialize(final Bootstrap<RentalsConfiguration> bootstrap) {
    }

    @Override
    public void run(final RentalsConfiguration configuration, final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        final FilmDao filmDao = jdbi.onDemand(FilmDao.class);
        final RentalDao rentalDao = jdbi.onDemand(RentalDao.class);
        final CustomerDao customerDao = jdbi.onDemand(CustomerDao.class);
        populateDatabase(filmDao, customerDao, rentalDao);
        environment.jersey().register(new RentalResource(filmDao, customerDao, rentalDao));
        environment.jersey().register(new FilmResource(filmDao));
        environment.jersey().register(new CustomerResource(customerDao));
    }

    //Providing this method for convenience only
    private void populateDatabase(FilmDao filmDao, CustomerDao customerDao, RentalDao rentalDao) {
        filmDao.createTable();
        filmDao.insert(Arrays.asList(
                new Film("Conan The Librarian", Film.FilmType.NEW_RELEASE),
                new Film("Ghandi II", Film.FilmType.OLDIE),
                new Film("101 Alsatians", Film.FilmType.REGULAR),
                new Film("Romcom", Film.FilmType.NEW_RELEASE),
                new Film("Horror", Film.FilmType.REGULAR)));
        customerDao.createTable();
        customerDao.insert(Arrays.asList(
                new Customer("Bob", 0, 10),
                new Customer("Pam", 10, 0),
                new Customer("Sue", 20, 0),
                new Customer("Jim", 50, 0)));
        rentalDao.createTable();
    }
}
