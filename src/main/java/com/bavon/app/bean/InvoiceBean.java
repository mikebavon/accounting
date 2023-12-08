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

        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        List<Object[]> customers = getDao().nativeQuery("select c.id, c.name from customers c");

        for (Object[] customer : customers){
            System.out.println("Customer ID " + customer[0]);
            System.out.println("Customer Name " + customer[1]);

            invoice.setCustomerId(((BigInteger) customer[0]).longValue());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

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
