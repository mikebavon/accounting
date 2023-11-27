package com.bavon.app.bean;

import com.bavon.app.model.Invoice;
import com.bavon.app.utility.InvoiceNoGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class InvoiceBean extends GenericBean<Invoice> implements InvoiceBeanI {

    @Inject
    private InvoiceNoGenerator invoiceNoGenerator;

    @Override
    public void addOrUpdate(Invoice invoice) {
        invoice.setInvoiceNo(invoiceNoGenerator.generate());
        getDao().addOrUpdate(invoice);

    }
}
