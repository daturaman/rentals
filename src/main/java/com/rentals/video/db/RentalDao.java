package com.rentals.video.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.rentals.video.api.Rental;

/**
 * Responsible for persisting Rentals to the database.
 * <p>
 * Created by michael on 29/04/17.
 */
@RegisterMapper(RentalMapper.class)
public interface RentalDao {
    @SqlUpdate("create table Rental (film varchar(100) primary key, customer varchar(100), due date, returned date null, days int, price int)")
    void createTable();

    @SqlUpdate("drop table Rental")
    void dropTable();

    @SqlBatch("update Rental set returned = current_date() where customer = :customer and film = :film")
    void markRentalsAsReturned(@BindBean List<Rental> rentals);

    @SqlBatch("insert into Rental (film, customer, due, days, price) values(:film, :customer, :due, :days, :price)")
    void insert(@BindBean List<Rental> rentals);

    @SqlQuery("select * from Rental where customer = :customer and returned is null")
    List<Rental> findDueRentalsForCustomer(@Bind("customer") String customer);
}
