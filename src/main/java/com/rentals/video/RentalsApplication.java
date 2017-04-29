package com.rentals.video;

import com.rentals.video.db.CustomerDao;
import com.rentals.video.db.FilmDao;
import com.rentals.video.db.RentalDao;
import com.rentals.video.resources.RentalResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

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
		// TODO: application initialization
	}

	@Override
	public void run(final RentalsConfiguration configuration, final Environment environment) {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        final FilmDao filmDao = jdbi.onDemand(FilmDao.class);
        final RentalDao rentalDao = jdbi.onDemand(RentalDao.class);
        final CustomerDao customerDao = jdbi.onDemand(CustomerDao.class);
        environment.jersey().register(new RentalResource(filmDao, customerDao, rentalDao));
    }
}
