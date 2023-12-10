package com.bavon.app.bean;

import com.bavon.app.model.Account;
import com.bavon.app.model.Journal;
import com.bavon.app.model.JournalLine;

import javax.ejb.Stateless;

@Stateless
public class JournalLineBean extends GenericBean<JournalLine> implements JournalLineBeanI{

    public JournalLine addOrUpdate(JournalLine journalLine) {
        if (journalLine == null || journalLine.getJournalId() == null)
            throw new RuntimeException("Invalid journal");

        if (journalLine.getAccountId() == null)
            throw new RuntimeException("Invalid Account");

        journalLine.setJournal(getDao().getEm().find(Journal.class, journalLine.getJournalId()));
        journalLine.setAccount(getDao().getEm().find(Account.class, journalLine.getAccountId()));

        return getDao().addOrUpdate(journalLine);
    }
}
