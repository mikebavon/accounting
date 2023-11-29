package com.bavon.app.bean;

import com.bavon.app.model.User;
import com.bavon.app.utility.EncryptSha256;
import com.bavon.app.utility.EncryptText;
import com.bavon.database.MysqlDatabase;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@Remote
public class AuthBean implements AuthBeanI, Serializable {

    @EJB
    MysqlDatabase database;

    @Inject
    private EncryptText encryptText;

    public User authenticate(User loginUser) {

        try {
            loginUser.setPassword(encryptText.encrypt(loginUser.getPassword()));
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

        List<User> users = database.fetch(loginUser);

        if (users.isEmpty() || users.get(0) == null)
            throw new RuntimeException("Invalid user!!");

        return users.get(0);
    }
}
