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

            PreparedStatement sqlStmt = MysqlDatabase.getInstance().getConnection()
                .prepareStatement("insert into users(id,username,password) values(?,?,?)");

            sqlStmt.setInt(1, ThreadLocalRandom.current().nextInt(1, 1000));
            sqlStmt.setString(2, user.getUsername());
            sqlStmt.setString(3, user.getPassword());

            sqlStmt.executeUpdate();

            return true;
        }

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
