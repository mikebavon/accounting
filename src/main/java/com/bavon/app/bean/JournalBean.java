package com.bavon.app.bean;

import com.bavon.app.model.Journal;
import com.bavon.app.utility.JournalNoGenerator;
import com.bavon.app.utility.JournalValidator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;

@Stateless
public class JournalBean extends GenericBean<Journal> implements JournalBeanI{

    @Inject
    private JournalNoGenerator journalNoGenerator;

    @Inject
    private JournalValidator journalValidator;

    @Override
    public void addOrUpdate(Journal journal) {
        if (journalValidator.inValid(journal) )
            throw new RuntimeException("Invalid journal");

        if (journal.getDate() == null)
            journal.setDate(new Date());

        journal.setJournalNo(journalNoGenerator.generate());
        getDao().addOrUpdate(journal);

    }

}
