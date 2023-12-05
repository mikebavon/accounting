package com.bavon.app.bean;

import com.bavon.app.model.AuditLog;
import com.bavon.app.model.User;
import com.bavon.app.utility.EncryptText;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Stateless
@Remote
public class AuthBean extends GenericBean<User> implements AuthBeanI, Serializable {

    @Inject
    private EncryptText encryptText;

    @Inject
    private Event<AuditLog> logger;

    public User authenticate(User loginUser) {

        try {
            loginUser.setPassword(encryptText.encrypt(loginUser.getPassword()));
        } catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

        List<User> users = list(loginUser);

        if (users.isEmpty() || users.get(0) == null)
            throw new RuntimeException("Invalid user!!");

        AuditLog log = new AuditLog();
        log.setLogDetails("User logged in at " + DateFormat.getDateTimeInstance().format(new Date())
            + ", " + users.get(0).getUsername());

        logger.fire(log);

        return users.get(0);
    }
}
