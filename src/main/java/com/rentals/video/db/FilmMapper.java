package com.rentals.video.db;

import com.rentals.video.api.Film;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mcarter
 */
public class FilmMapper implements ResultSetMapper<Film> {

	@Override
	public Film map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        return new Film(resultSet.getString("title"),
                Film.FilmType.valueOf(resultSet.getString("type")));
	}
}
