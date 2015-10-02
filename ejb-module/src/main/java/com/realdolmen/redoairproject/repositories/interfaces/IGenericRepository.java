package com.realdolmen.redoairproject.repositories.interfaces;

import javax.ejb.Remote;
import java.util.List;

public interface IGenericRepository<T> {

    T create(T t);
    T findById(Long id);
    T update(T t);
    void delete(T t);
    void refresh(T t);
    List<T> findAll();
}
