package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Address;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.IAddressRepository;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by YDEAX41 on 6/10/2015.
 */
public class AddressRepository extends GenericRepository<Address> implements IAddressRepository {

    @Override public List<Address> findAddressesByCountry(Country country) {
        return null;
    }
}
