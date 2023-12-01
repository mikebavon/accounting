package com.bavon.app.utility;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Named("Invoice")
public class InvoiceNoGenerator implements TransactionNoGenerator{

    @Inject
    @TransactionNo(type = TransactionType.INVOICE)
    private int invoiceNoInfo;

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");

        return "INV" + dateFormat.format(new Date()) + "-" + invoiceNoInfo;
    }

}
