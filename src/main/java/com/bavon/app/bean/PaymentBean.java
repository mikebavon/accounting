package com.bavon.app.bean;

import com.bavon.app.model.Payment;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@Remote
public class PaymentBean extends GenericBean<Payment> implements PaymentBeanI {

    @Inject
    @Named("Payment")
    private TransactionNoGenerator txnNoGenerator;

    public Payment addOrUpdate(Payment payment) {
        payment.setVoucherNo(txnNoGenerator.generate());
        return getDao().addOrUpdate(payment);
    }
}
