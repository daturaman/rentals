package com.rentals.video.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.rentals.video.api.Film;

/**
 * @author mcarter
 */
public class FilmMapper implements ResultSetMapper<Film> {

	@Override
	public Film map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
		return new Film(resultSet.getInt("id"), resultSet.getString("title"),
				Film.FilmType.valueOf(resultSet.getString("type")));
	}
}
