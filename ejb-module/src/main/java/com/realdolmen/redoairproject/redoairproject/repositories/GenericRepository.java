package com.realdolmen.redoairproject.redoairproject.repositories;

import com.realdolmen.redoairproject.redoairproject.entities.AbstractEntity;
import com.realdolmen.redoairproject.redoairproject.repositories.interfaces.IGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class GenericRepository<T extends AbstractEntity> implements IGenericRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T create(T t)
    {
        entityManager.persist(t);
        entityManager.flush();
        return t;
    }

    @Override
    public T findById(Class<T> persistentClass, Long id) {
        return entityManager.find(persistentClass, id);
    }

    @Override
    public T update(T t) {
        return entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        t = entityManager.merge(t);
        entityManager.remove(t);
    }

    @Override
    public void refresh(T t) {
    }

    @Override
    public List<T> findAll(Class<T> persistentClass) {
        String queryString = "Select t from " + persistentClass.getSimpleName() + " t";
        TypedQuery<T> query = entityManager.createQuery(queryString, persistentClass);

        return query.getResultList();
    }
}
