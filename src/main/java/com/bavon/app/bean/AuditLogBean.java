package com.bavon.app.bean;


import com.bavon.app.model.AuditLog;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Singleton
public class AuditLogBean implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void log(@Observes AuditLog auditLog){
        System.out.println("Adding audit log!!");
        em.merge(auditLog);
    }

}
