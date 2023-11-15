package com.bavon.app.bean;

import com.bavon.app.model.User;
import com.bavon.database.Database;

import java.io.Serializable;

public class AuthBean implements AuthBeanI, Serializable {

    Database database = Database.getDbInstance();

    public User authenticate(User loginUser) {
        return (User) database.getData(User.class)
            .stream()
            .filter(user -> ((User)user).getUsername().equals(loginUser.getUsername())
                    && ((User)user).getPassword().equals(loginUser.getPassword()))
            .findAny()
            .orElse(null);
    }
}
