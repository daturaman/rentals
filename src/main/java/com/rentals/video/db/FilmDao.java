package com.rentals.video.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.rentals.video.api.Film;

/**
 * DAO for crud operations on Films.
 *
 * @author mcarter
 */
@RegisterMapper(FilmMapper.class)
public interface FilmDao {
	@SqlUpdate("create table Film (id int primary key, title varchar(100), type varchar(100))")
	void createTable();

	@SqlBatch("insert into Film (id, title, type) values(:id, :title, :type)")
	void insert(@BindBean List<Film> films);

	@SqlQuery("select * from Film where title = :title")
	Film findByTitle(@Bind("title") String title);
}
