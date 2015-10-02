package com.realdolmen.redoairproject.redoairproject.repositories.interfaces;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by YDEAX41 on 10/09/2015.
 */
@Remote
public interface IGenericRepository<T> {

    T create(T t);
    T findById(Class<T> persistentClass, Long id);
    T update(T t);
    void delete(T t);
    void refresh(T t);
    List<T> findAll(Class<T> persistentClass);
}
