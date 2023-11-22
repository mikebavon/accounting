package com.bavon.app.bean;

import com.bavon.app.model.Journal;

import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class JournalBean extends GenericBean<Journal> implements JournalBeanI{

    @Override
    public void addOrUpdate(Journal journal) {
        if (journal.getDebitBalance() == null || journal.getCreditBalance() == null ||
            journal.getDebitBalance().compareTo(journal.getCreditBalance()) !=0 )
            throw new RuntimeException("Invalid journal");

        if (journal.getDate() == null)
            journal.setDate(new Date());

        journal.setJournalNo(new Date().getTime() + "");
        getDao().addOrUpdate(journal);

    }

}
