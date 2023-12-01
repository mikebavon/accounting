package com.bavon.app.bean;

import com.bavon.app.model.AuditLog;
import com.bavon.app.model.Invoice;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.util.Date;

@Stateless
@Remote
public class InvoiceBean extends GenericBean<Invoice> implements InvoiceBeanI {

    @Inject
    @Named("Invoice")
    private TransactionNoGenerator txnNoGenerator;

    @Inject
    private Event<AuditLog> logger;

    @Override
    public void addOrUpdate(Invoice invoice) {
        invoice.setInvoiceNo(txnNoGenerator.generate());
        getDao().addOrUpdate(invoice);



        AuditLog log = new AuditLog();
        log.setLogDetails("A Invoice " + invoice.getInvoiceNo() + " was added at "
            + DateFormat.getDateTimeInstance().format(new Date()));

        logger.fire(log);
    }
}
