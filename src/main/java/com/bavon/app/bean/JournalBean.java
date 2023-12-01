package com.bavon.app.bean;

import com.bavon.app.model.AuditLog;
import com.bavon.app.model.Journal;
import com.bavon.app.utility.JournalValidator;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.util.Date;

@Stateless
public class JournalBean extends GenericBean<Journal> implements JournalBeanI{

    @Inject
    @Named("Journal")
    private TransactionNoGenerator txnNoGenerator;

    @Inject
    private JournalValidator journalValidator;

    @Inject
    private Event<AuditLog> logger;

    @Override
    public void addOrUpdate(Journal journal) {
        if (journalValidator.inValid(journal))
            throw new RuntimeException("Invalid journal");

        if (journal.getDate() == null)
            journal.setDate(new Date());

        journal.setJournalNo(txnNoGenerator.generate());

        getDao().addOrUpdate(journal);

        AuditLog log = new AuditLog();
        log.setLogDetails("A journal " + journal.getJournalNo() + " was added at "
            + DateFormat.getDateTimeInstance().format(new Date()));

        logger.fire(log);

    }

    @Inject
    public void logInjection(){
        System.out.println("this method is executed through injection...0");
    }

    @Inject
    public void logInjection1(){
        System.out.println("this method is executed through injection....1");
    }

    @Inject
    public void logInjection2(){
        System.out.println("this method is executed through injection....2");
    }

}
