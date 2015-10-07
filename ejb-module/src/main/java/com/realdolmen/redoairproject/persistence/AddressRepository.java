package com.realdolmen.redoairproject.persistence;

import com.realdolmen.redoairproject.entities.Address;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;
import com.realdolmen.redoairproject.persistence.interfaces.IAddressRepository;
import com.realdolmen.redoairproject.persistence.interfaces.ITripRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@LocalBean
@Stateless
public class AddressRepository extends GenericRepository<Address> implements IAddressRepository {
    public AddressRepository() {
        super(Address.class);
    }
}
