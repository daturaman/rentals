package com.rentals.video.db;

import com.rentals.video.api.Rental;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mcarter
 */
public class RentalMapper implements ResultSetMapper<Rental> {

    @Override
    public Rental map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new Rental(resultSet.getString("customer"), resultSet.getString("film"),
                resultSet.getInt("days"), resultSet.getInt("price"), resultSet.getDate("due"), resultSet.getDate("returned"));
    }
}
