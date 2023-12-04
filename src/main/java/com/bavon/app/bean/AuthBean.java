package com.bavon.app.bean;

import com.bavon.app.model.AuditLog;
import com.bavon.app.model.User;
import com.bavon.app.utility.EncryptText;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Stateless
@Remote
public class AuthBean implements AuthBeanI, Serializable {

    @PersistenceContext
    EntityManager em;

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

        List<User> users = em.createQuery("FROM User u WHERE u.password=:password AND u.username=:username", User.class)
                .setParameter("password", loginUser.getPassword())
                .setParameter("username", loginUser.getUsername())
                .getResultList();

        if (users.isEmpty() || users.get(0) == null)
            throw new RuntimeException("Invalid user!!");

        AuditLog log = new AuditLog();
        log.setLogDetails("User logged in at " + DateFormat.getDateTimeInstance().format(new Date())
            + ", " + users.get(0).getUsername());

        logger.fire(log);

        return users.get(0);
    }
}
