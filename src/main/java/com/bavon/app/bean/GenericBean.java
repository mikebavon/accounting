package com.bavon.app.bean;

import com.bavon.app.model.*;
import com.bavon.database.Database;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class GenericBean<T> implements GenericBeanI<T>{

    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public List<T> list() {
        Class clazz = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);

        System.out.println("List class " + clazz);
        if (clazz.equals(Account.class))
            return (List<T>) Database.getDbInstance().getAccounts();

        if (clazz.equals(Journal.class))
            return (List<T>) Database.getDbInstance().getJournals();

        if (clazz.equals(Customer.class))
            return (List<T>) Database.getDbInstance().getCustomers();

        return  new ArrayList<>();
    }

    @Override
    public T addOrUpdateAccount(T entity) {
        Database database = Database.getDbInstance();

        Class clazz = entity.getClass();
        System.out.println(clazz.getName());

        if (entity instanceof Account)
            database.getAccounts().add((Account) entity);

        else if (entity instanceof Journal)
            database.getJournals().add((Journal) entity);

        else if (entity instanceof User)
            database.getUsers().add((User) entity);

        return entity;
    }

    @Override
    public void deleteAccount(T entity) {

    }
}
