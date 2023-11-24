package com.bavon.app.dao;


import com.bavon.database.MysqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {

    private MysqlDatabase database;


    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> list(Object entity) {
        return (List<T>) database.fetch(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        database.saveOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MysqlDatabase database) {
        this.database = database;
    }
}
