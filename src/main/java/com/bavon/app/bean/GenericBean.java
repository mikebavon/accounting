package com.bavon.app.bean;

import com.bavon.database.Database;
import com.bavon.database.MysqlDatabase;

import java.util.List;

public class GenericBean<T> implements GenericBeanI<T>{

    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) Database.getDbInstance().getData(entity);

    }

    @Override
    public void addOrUpdateAccount(T entity) {
        MysqlDatabase.insert(entity);

    }

    @Override
    public void deleteAccount(T entity) {

    }
}
