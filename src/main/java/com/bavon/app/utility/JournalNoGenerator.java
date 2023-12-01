package com.bavon.app.utility;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("Journal")
public class JournalNoGenerator implements TransactionNoGenerator{

    @Inject
    @TransactionNo(type = TransactionType.JOURNAL)
    private int journalNoInfo;

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "JNL" + dateFormat.format(new Date()) + "-" + journalNoInfo;
    }
}
