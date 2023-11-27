package com.bavon.app.utility;

import com.bavon.app.model.Journal;

public class JournalValidator {

    public boolean valid(Journal journal){
        return !(journal.getDebitBalance() == null || journal.getCreditBalance() == null ||
            journal.getDebitBalance().compareTo(journal.getCreditBalance()) !=0);
    }

    public boolean inValid(Journal journal){
        return !this.valid(journal);
    }
}
