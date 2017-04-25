package com.rentals.video;

import io.dropwizard.Application;
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
    public void run(final RentalsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
