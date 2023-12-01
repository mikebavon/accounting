package com.bavon.app.utility;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Named("Payment")
public class PaymentNoGenerator implements TransactionNoGenerator{

    @Inject
    @TransactionNo(type = TransactionType.INVOICE)
    private int paymentNoInfo;

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "PMT" + dateFormat.format(new Date()) + "-" + paymentNoInfo;
    }
}
