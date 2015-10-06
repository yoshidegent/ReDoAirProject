package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Address;
import com.realdolmen.redoairproject.entities.Airline;
import com.realdolmen.redoairproject.persistence.interfaces.IAddressRepository;
import com.realdolmen.redoairproject.persistence.interfaces.IAirlineRepository;

/**
 * Created by EWTAX45 on 6/10/2015.
 */
public class AddressRepository extends GenericRepository<Address> implements IAddressRepository {

    public AddressRepository() {
        super(Address.class);
    }

}
