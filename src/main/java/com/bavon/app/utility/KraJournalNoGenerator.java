package com.bavon.app.utility;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@Alternative
public class KraJournalNoGenerator extends JournalNoGenerator{

    @Inject
    @TransactionNo(type = TransactionType.JOURNAL)
    private int journalNoInfo;

    @Override
    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "KRA" + dateFormat.format(new Date()) + "-" + journalNoInfo;
    }
}
