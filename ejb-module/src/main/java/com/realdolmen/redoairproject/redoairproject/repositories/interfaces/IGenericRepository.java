package com.realdolmen.redoairproject.redoairproject.repositories.interfaces;

import javax.ejb.Remote;
import java.util.List;

public interface IGenericRepository<T> {

    T createOrUpdate(T t);
    T findById(Long id);
    void delete(T t);
    void refresh(T t);
    List<T> findAll();
}
