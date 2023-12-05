package com.bavon.app.bean;

import com.bavon.app.dao.GenericDao;
import com.bavon.app.dao.GenericDaoI;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericBean<T> implements GenericBeanI<T>{

    @PersistenceContext
    private EntityManager em;

    @Inject
    private GenericDaoI<T> genericDao;

    @Override
    public List<T> list(T entity) {
        genericDao.setEm(em);
        return genericDao.list(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        genericDao.setEm(em);
        genericDao.addOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao(){
        genericDao.setEm(em);
        return (GenericDao<T>) genericDao;
    }

}
