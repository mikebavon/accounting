package com.bavon.app.bean;

import com.bavon.app.model.User;
import com.bavon.database.MysqlDatabase;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
@Remote
public class AuthBean implements AuthBeanI, Serializable {

    public User authenticate(User loginUser) {

        List<User> users = MysqlDatabase.fetch(loginUser);

        if (users.isEmpty() || users.get(0) == null)
            throw new RuntimeException("Invalid user!!");

        return users.get(0);
    }
}
