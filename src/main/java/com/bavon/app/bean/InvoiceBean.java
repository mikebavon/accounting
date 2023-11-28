package com.bavon.app.bean;

import com.bavon.app.model.Invoice;
import com.bavon.app.utility.InvoiceNo;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class InvoiceBean extends GenericBean<Invoice> implements InvoiceBeanI {

    @Inject
    @InvoiceNo
    private TransactionNoGenerator txnNoGenerator;

    @Override
    public void addOrUpdate(Invoice invoice) {
        invoice.setInvoiceNo(txnNoGenerator.generate());
        getDao().addOrUpdate(invoice);

    }
}
