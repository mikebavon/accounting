package com.bavon.app.bean;

import com.bavon.app.model.Journal;

import java.util.Date;

public class JournalBean extends GenericBean<Journal> implements JournalBeanI{

    @Override
    public void addOrUpdate(Journal journal) {
        if (journal.getDebitBalance() == null || journal.getCreditBalance() == null ||
            journal.getDebitBalance().compareTo(journal.getCreditBalance()) !=0 )
            throw new RuntimeException("Invalid journal");


        journal.setJournalNo(new Date().getTime() + "");
        getDao().addOrUpdate(journal);

    }

}
