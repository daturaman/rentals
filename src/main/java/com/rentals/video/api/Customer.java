/*
 * <copyright>
 *
 * Copyright (c) 2010-2017 Gresham Technologies plc. All rights reserved.
 *
 * </copyright>
 */
package com.rentals.video.api;

import java.util.Collection;

/**
 * @author mcarter
 */
public class Customer {
	Collection<Rental> rentals;
	int points;
	int fines;
}
