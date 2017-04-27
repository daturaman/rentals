package com.rentals.video;

import org.skife.jdbi.v2.DBI;

import com.rentals.video.db.FilmDao;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
		final FilmDao dao = jdbi.onDemand(FilmDao.class);
		//FIXME environment.jersey().register(new FilmService(dao));
	}
}
