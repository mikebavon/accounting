package com.bavon.app.bean;

import com.bavon.app.model.Invoice;

import java.util.Date;

public class InvoiceBean extends GenericBean<Invoice> implements InvoiceBeanI {


    @Override
    public void addOrUpdate(Invoice invoice) {
        invoice.setInvoiceNo(new Date().getTime() + "");
        getDao().addOrUpdate(invoice);

    }
}
