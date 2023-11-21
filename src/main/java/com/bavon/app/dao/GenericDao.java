package com.bavon.app.dao;

import com.bavon.database.Database;
import com.bavon.database.MysqlDatabase;

import java.util.List;

public class GenericDao<T> implements GenericDaoI<T> {

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) MysqlDatabase.select(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        MysqlDatabase.saveOrUpdate(entity);

    }

    @Override
    public void delete(T entity) {

    }
}
