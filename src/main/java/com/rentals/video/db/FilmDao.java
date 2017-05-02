package com.rentals.video.db;

import com.rentals.video.api.Film;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

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

    @SqlQuery("select * from Film")
    List<Film> findAll();
}
