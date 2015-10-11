package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Booking;

import javax.ejb.Remote;

/**
 * Created by EWTAX45 on 11/10/2015.
 */
@Remote
public interface IBookingRepository extends IGenericRepository<Booking> {
}
