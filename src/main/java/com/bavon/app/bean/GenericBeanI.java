package com.bavon.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {

    List<T> list(T entity);

    void addOrUpdate(T entity);

    void delete(T entity);

}
