package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Booking;
import com.realdolmen.redoairproject.persistence.interfaces.IBookingRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.io.Serializable;

/**
 * Created by EWTAX45 on 11/10/2015.
 */
@Stateless
@LocalBean
public class BookingRepository extends GenericRepository<Booking> implements IBookingRepository, Serializable {
    public BookingRepository() {
        super(Booking.class);
    }

}
