package com.bavon.app.bean;

import com.bavon.app.model.*;
import com.bavon.app.utility.TransactionNoGenerator;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

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

        if (invoice.getInvoiceDate() == null)
            invoice.setInvoiceDate(new Date());

        if (invoice.getCustomerId() == null)
            throw new RuntimeException("Customer is required!");

        invoice.setCustomer(getDao().getEm().find(Customer.class, invoice.getCustomerId()));

        if (invoice.getCustomer() == null)
            throw new RuntimeException("Invalid customer details!");

        invoice.setInvoiceNo(txnNoGenerator.generate());

        Journal journal = new Journal();
        journal.setMemo(invoice.getNarration());
        journal.setDate(invoice.getInvoiceDate());

        JournalLine debit = new JournalLine();
        debit.setAccount(getDao().getEm().find(Account.class, invoice.getDebitAccountId()));
        debit.setDebit(invoice.getTotal());
        debit.setNarration(invoice.getNarration());
        journal.addJournalLine(debit);

        JournalLine credit = new JournalLine();
        credit.setAccount(getDao().getEm().find(Account.class, invoice.getCreditAccountId()));
        credit.setCredit(invoice.getTotal());
        credit.setNarration(invoice.getNarration());
        journal.addJournalLine(credit);

        invoice.setJournal(journalBean.addOrUpdate(journal));

        invoice = getDao().addOrUpdate(invoice);

        AuditLog log = new AuditLog();
        log.setLogDetails("A Invoice " + invoice.getInvoiceNo() + " was added at "
            + DateFormat.getDateTimeInstance().format(new Date()));

        logger.fire(log);

        List<Object[]> invoiceAndCustomers = getDao().nativeQuery("select i.id as iId,i.total,c.id as cId,c.name from invoices i " +
                "inner join customers c on c.id=i.customer_id");

        for (Object [] invoiceAndCustomer : invoiceAndCustomers) {
            System.out.println(invoiceAndCustomer[0] + ". TOTAL " + invoiceAndCustomer[1]
                + " CUSTOMER ID: " + invoiceAndCustomer[2] + " CUSTOMER NAME: " + invoiceAndCustomer[3]);
        }

        List<Invoice> invoicesAbove100 = getDao().getEm().createNamedQuery(Invoice.InvoiceBelow1000, Invoice.class).getResultList();
        for (Invoice inv1000 : invoicesAbove100){
            System.out.println(inv1000.getInvoiceNo() + " " + inv1000.getTotal());
        }

        return invoice;
    }
}
