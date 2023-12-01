package com.bavon.app.utility;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class TransactionNoInfo {


    @Produces
    @TransactionNo(type = TransactionType.JOURNAL)
    int journalNoInfo(){
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1);
    }

    @Produces
    @TransactionNo(type = TransactionType.INVOICE)
    int invoiceNoInfo(){
        return ThreadLocalRandom.current().nextInt(1000, 2000 + 1);
    }

    @Produces
    @TransactionNo(type = TransactionType.PAYMENT)
    int paymentNoInfo(){
        return ThreadLocalRandom.current().nextInt(2000, 3000 + 1);
    }
}
