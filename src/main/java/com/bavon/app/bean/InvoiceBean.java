package com.bavon.app.bean;

import com.bavon.app.model.AuditLog;
import com.bavon.app.model.Customer;
import com.bavon.app.model.Invoice;
import com.bavon.app.model.Journal;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.EJB;
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

    @EJB
    private JournalBeanI journalBean;

    @Inject
    private Event<AuditLog> logger;

    @Override
    public Invoice addOrUpdate(Invoice invoice) {
        if (invoice == null)
            throw new RuntimeException("Invalid invoice details!");

        if (invoice.getCustomerId() == null)
            throw new RuntimeException("Customer is required!");

        invoice.setCustomer(getDao().getEm().find(Customer.class, invoice.getCustomerId()));

        if (invoice.getCustomer() == null)
            throw new RuntimeException("Invalid customer details!");

        invoice.setInvoiceNo(txnNoGenerator.generate());

        Journal journal = new Journal();
        journal.setMemo(invoice.getNarration());
        journal.setDate(invoice.getInvoiceDate());
        journal.setDebitBalance(invoice.getTotal());
        journal.setCreditBalance(invoice.getTotal());

        invoice.setJournal(journalBean.addOrUpdate(journal));

        invoice = getDao().addOrUpdate(invoice);

        AuditLog log = new AuditLog();
        log.setLogDetails("A Invoice " + invoice.getInvoiceNo() + " was added at "
            + DateFormat.getDateTimeInstance().format(new Date()));

        logger.fire(log);

        return invoice;
    }
}
