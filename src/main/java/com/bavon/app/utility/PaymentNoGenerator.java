package com.bavon.app.utility;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Named("Payment")
public class PaymentNoGenerator implements TransactionNoGenerator{

    public String generate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        return "PMT" + dateFormat.format(new Date()) + "-" + ThreadLocalRandom.current().nextInt(2000, 3000 + 1);
    }
}
