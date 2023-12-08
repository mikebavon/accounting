package com.bavon.app.bean;

import com.bavon.app.model.*;
import com.bavon.app.utility.JournalValidator;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Stateless
public class JournalBean extends GenericBean<Journal> implements JournalBeanI{

    @Inject
    @Named("Journal")
    private TransactionNoGenerator txnNoGenerator;

    @Inject
    private JournalValidator journalValidator;

    @Inject
    private Event<AuditLog> logger;

    public Journal addOrUpdate(Journal journal) {
        if (journalValidator.inValid(journal))
            throw new RuntimeException("Invalid journal");

        JournalLine debit = new JournalLine();
        debit.setNarration(journal.getMemo());
        debit.setDebit(journal.getDebitBalance());
        journal.addJournalLine(debit);

        JournalLine credit = new JournalLine();
        credit.setNarration(journal.getMemo());
        credit.setCredit(journal.getDebitBalance());
        journal.addJournalLine(credit);

        if (journal.getDate() == null)
            journal.setDate(new Date());

        journal.setJournalNo(txnNoGenerator.generate());

        journal = getDao().addOrUpdate(journal);

        AuditLog log = new AuditLog();
        log.setLogDetails("A journal " + journal.getJournalNo() + " was added at "
            + DateFormat.getDateTimeInstance().format(new Date()));

        logger.fire(log);

        return journal;

    }

    @Override
    public List<Journal> list(Journal entity) {

        List<Journal> journals = super.list(entity);

        for (Journal journal : journals) {
            StringBuilder entryTable = new StringBuilder("<table><tr><th>Debit</th><th>Credit</th><tr>");
            for (JournalLine line : journal.getJournalLines() ){
                entryTable.append("<tr><td>" + (line.getDebit() == null? "" : line.getDebit()) + "</td><td>"
                    + (line.getCredit() == null? "" : line.getCredit()) + "</td><tr>");
            }

            entryTable.append("</table>");

            journal.setJournalLineText(entryTable.toString());
        }

        return journals;

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
