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
    @SqlUpdate("create table Film (title varchar(100) primary key, type varchar(100))")
    void createTable();

	@SqlUpdate("drop table Film")
	void dropTable();

    @SqlBatch("insert into Film values(:title, :type)")
    void insert(@BindBean List<Film> films);

	@SqlQuery("select * from Film where title = :title")
	Film findByTitle(@Bind("title") String title);
}
