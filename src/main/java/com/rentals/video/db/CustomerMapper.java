package com.rentals.video.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.rentals.video.api.Customer;

/**
 * @author mcarter
 */
public class CustomerMapper implements ResultSetMapper<Customer> {

	@Override
	public Customer map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
		return new Customer(resultSet.getString("name"),
				resultSet.getInt("points"), resultSet.getInt("fines"));
	}
}
