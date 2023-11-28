package com.bavon.app.bean;

import com.bavon.app.model.Payment;
import com.bavon.app.utility.PaymentNo;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class PaymentBean extends GenericBean<Payment> implements PaymentBeanI {

    @Inject
    @PaymentNo
    private TransactionNoGenerator txnNoGenerator;

    @Override
    public void addOrUpdate(Payment payment) {
        payment.setVoucherNo(txnNoGenerator.generate());
        getDao().addOrUpdate(payment);

    }
}
