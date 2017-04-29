package com.rentals.video.db;

import com.rentals.video.api.Customer;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author mcarter
 */
@RegisterMapper(CustomerMapper.class)
public interface CustomerDao {
	@SqlUpdate("create table Customer (name varchar(100), points int default 0, fines int default 0)")
	void createTable();

    @SqlBatch("insert into Customer values(:name, :points, :fines)")
    void insert(@BindBean List<Customer> customers);

	@SqlQuery("select * from Customer where name = :name")
	Customer findByName(@Bind("name") String name);
}
