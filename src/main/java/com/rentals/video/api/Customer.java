package com.rentals.video.api;

/**
 * @author mcarter
 */
public class Customer {

	private String name;
	private int points;
	private int fines;

	public Customer(String name, int points, int fines) {
		this.name = name;
		this.points = points;
		this.fines = fines;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public int getFines() {
		return fines;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer customer = (Customer) o;
		if (getPoints() != customer.getPoints())
			return false;
		if (getFines() != customer.getFines())
			return false;
		return getName().equals(customer.getName());
	}

	@Override
	public int hashCode() {
		int result = getName().hashCode();
		result = 31 * result + getPoints();
		result = 31 * result + getFines();
		return result;
	}
}
