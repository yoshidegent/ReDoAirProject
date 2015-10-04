package com.realdolmen.redoairproject.persistence.interfaces;

import java.util.List;

public interface IGenericRepository<T> {

    T createOrUpdate(T t);
    T findById(Long id);
    void delete(T t);
    List<T> findAll();
}
