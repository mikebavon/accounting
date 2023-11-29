package com.bavon.app.utility;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Named("Invoice")
public class InvoiceNoGenerator implements TransactionNoGenerator{

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");

        return "INV" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
    }

}
