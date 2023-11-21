package com.bavon.app.bean;

import com.bavon.app.dao.GenericDao;
import com.bavon.app.dao.GenericDaoI;

import java.util.List;

public class GenericBean<T> implements GenericBeanI<T>{

    private final GenericDaoI<T> genericDao = new GenericDao<>();

    @Override
    public List<T> list(Class<?> entity) {
        return genericDao.list(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        genericDao.addOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao(){
        return (GenericDao<T>) genericDao;
    }

}
