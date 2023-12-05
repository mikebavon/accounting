package com.bavon.app.bean;

import com.bavon.app.model.User;
import com.bavon.app.utility.EncryptText;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Stateless
@Remote
public class UserBean extends GenericBean<User> implements UserBeanI {

    @Inject
    private EncryptText encryptText;

    @Override
    public boolean register(User user) throws SQLException {

        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new RuntimeException("Password & confirm password do not match");

        List<User> checkUser = list(new User(user.getUsername()));
        if (!checkUser.isEmpty())
            throw new RuntimeException("User already exists!");

        try {
            user.setPassword(encryptText.encrypt(user.getPassword()));

        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());

        }

        //3. initiate event to send email ...Observer design pattern
        getDao().addOrUpdate(user);

        return false;
    }

    @Override
    public boolean unregister(User user) {
        return true;
    }
}
