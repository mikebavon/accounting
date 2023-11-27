package com.bavon.app.bean;

import com.bavon.app.dao.GenericDao;
import com.bavon.app.dao.GenericDaoI;
import com.bavon.database.MysqlDatabase;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

public abstract class GenericBean<T> implements GenericBeanI<T>{

    @EJB
    MysqlDatabase database;

    @Inject
    private GenericDaoI<T> genericDao;

    @Override
    public List<T> list(Object entity) {
        genericDao.setDatabase(database);
        return genericDao.list(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        genericDao.setDatabase(database);
        genericDao.addOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }

    public GenericDao<T> getDao(){
        genericDao.setDatabase(database);
        return (GenericDao<T>) genericDao;
    }

}
