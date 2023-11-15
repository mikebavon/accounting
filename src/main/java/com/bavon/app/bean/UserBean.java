package com.bavon.app.bean;

import com.bavon.app.model.User;
import com.bavon.database.Database;

import java.io.Serializable;

public class UserBean implements UserBeanI, Serializable {

    Database database = Database.getDbInstance();

    @Override
    public boolean register(User user) {

        if (user.getPassword().equals(user.getConfirmPassword())) {
            database.getData().add(new User(100L, user.getUsername(), user.getPassword()));

            return true;
        }

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
