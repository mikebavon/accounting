package com.bavon.app.utility;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Named("Journal")
public class JournalNoGenerator implements TransactionNoGenerator{

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "JNL" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(0, 1000 + 1);
    }
}
