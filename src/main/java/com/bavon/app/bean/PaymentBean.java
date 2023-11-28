package com.bavon.app.bean;

import com.bavon.app.model.Payment;
import com.bavon.app.utility.TransactionNo;
import com.bavon.app.utility.TransactionNoGenerator;
import com.bavon.app.utility.TransactionType;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class PaymentBean extends GenericBean<Payment> implements PaymentBeanI {

    @Inject
    @TransactionNo(type = TransactionType.PAYMENT)
    private TransactionNoGenerator txnNoGenerator;

    @Override
    public void addOrUpdate(Payment payment) {
        payment.setVoucherNo(txnNoGenerator.generate());
        getDao().addOrUpdate(payment);

    }
}
