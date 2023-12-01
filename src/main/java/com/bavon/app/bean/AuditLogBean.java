package com.bavon.app.bean;


import com.bavon.app.model.AuditLog;
import com.bavon.database.MysqlDatabase;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.io.Serializable;

@Singleton
public class AuditLogBean implements Serializable {

    @EJB
    MysqlDatabase database;

    public void log(@Observes AuditLog auditLog){
        System.out.println("Adding audit log!!");
        database.saveOrUpdate(auditLog);
    }

}
