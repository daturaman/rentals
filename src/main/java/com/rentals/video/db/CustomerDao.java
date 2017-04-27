package com.rentals.video.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.rentals.video.api.Customer;

/**
 * @author mcarter
 */
@RegisterMapper(CustomerMapper.class)
public interface CustomerDao {
	@SqlUpdate("create table Customer (name varchar(100), points int default 0, fines int default 0)")
	void createTable();

	@SqlBatch("insert into Customer values(:name)")
	void insert(@BindBean List<Customer> customers);

	@SqlQuery("select * from Customer where name = :name")
	Customer findByName(@Bind("name") String name);


}
