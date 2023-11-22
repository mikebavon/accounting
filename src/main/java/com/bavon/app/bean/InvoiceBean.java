package com.bavon.app.bean;

import com.bavon.app.model.Invoice;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
@Remote
public class InvoiceBean extends GenericBean<Invoice> implements InvoiceBeanI {


    @Override
    public void addOrUpdate(Invoice invoice) {
        invoice.setInvoiceNo(new Date().getTime() + "");
        getDao().addOrUpdate(invoice);

    }
}
