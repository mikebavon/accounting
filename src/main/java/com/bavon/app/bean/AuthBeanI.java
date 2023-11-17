package com.bavon.app.bean;

import com.bavon.app.model.User;

import java.sql.SQLException;

public interface AuthBeanI {

    User authenticate(User loginUser) throws SQLException;
}
