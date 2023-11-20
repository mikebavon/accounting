package com.bavon.app.bean;

import com.bavon.app.model.User;
import com.bavon.database.Database;
import com.bavon.database.MysqlDatabase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class UserBean implements UserBeanI, Serializable {



    @Override
    public boolean register(User user) throws SQLException {

        if (user.getPassword().equals(user.getConfirmPassword())) {

            MysqlDatabase.insert(user);

            return true;
        }

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
