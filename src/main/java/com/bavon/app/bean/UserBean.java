package com.bavon.app.bean;

import com.bavon.app.model.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.SQLException;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {

    @Override
    public boolean register(User user) throws SQLException {

        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new RuntimeException("Password & confirm password do not match");

        //1. check if username already exist
        //2. hash password
        //3. initiate event to send email ...Observer design pattern

        getDao().addOrUpdate(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
