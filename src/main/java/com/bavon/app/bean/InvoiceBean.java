package com.bavon.app.bean;

import com.bavon.app.model.Invoice;
import com.bavon.app.utility.TransactionNo;
import com.bavon.app.utility.TransactionNoGenerator;
import com.bavon.app.utility.TransactionType;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class InvoiceBean extends GenericBean<Invoice> implements InvoiceBeanI {

    @Inject
    @TransactionNo(type = TransactionType.INVOICE)
    private TransactionNoGenerator txnNoGenerator;

    @Override
    public void addOrUpdate(Invoice invoice) {
        invoice.setInvoiceNo(txnNoGenerator.generate());
        getDao().addOrUpdate(invoice);

    }
}
