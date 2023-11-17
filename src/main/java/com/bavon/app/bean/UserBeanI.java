package com.bavon.app.bean;

import com.bavon.app.model.User;

import java.sql.SQLException;

public interface UserBeanI {

    boolean register(User user) throws SQLException;

    boolean unregister(User user);
}
