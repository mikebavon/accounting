package com.bavon.app.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface GenericDaoI <T> extends Serializable {

    List<T> list(T entity);

    void addOrUpdate(T entity);

    void delete(T entity);

    EntityManager getEm();

    void setEm(EntityManager em);

}
