package com.rentals.video.db;

import com.rentals.video.api.Rental;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Responsible for persisting Rentals to the database.
 * <p>
 * Created by michael on 29/04/17.
 */
@RegisterMapper(RentalMapper.class)
public interface RentalDao {
    @SqlUpdate("create table Rental (film varchar(100) primary key, customer varchar(100), due date, returned date null, days int, price int)")
    void createTable();

    @SqlBatch("update Rental set returned = current_date() where customer = :customer and film = :film")
    void markRentalsAsReturned(@BindBean List<Rental> rentals);

    @SqlBatch("insert into Rental (film, customer, due, days, price) values(:film, :customer, :due, :days, :price)")
    void insert(@BindBean List<Rental> rentals);

    @SqlQuery("select * from Rental where customer = :customer and returned is null")
    List<Rental> findDueRentalsForCustomer(@Bind("customer") String customer);
}
