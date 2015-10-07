package com.realdolmen.redoairproject.persistence.interfaces;

import com.realdolmen.redoairproject.entities.Address;
import com.realdolmen.redoairproject.entities.Airport;
import com.realdolmen.redoairproject.entities.Country;
import com.realdolmen.redoairproject.entities.Trip;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface IAddressRepository extends IGenericRepository<Address>
{
}
