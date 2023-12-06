package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "invoices")
@HtmlTable(addUrl = "./invoices?action=add")
@HtmlForm(label = "Invoice", url = "./invoices")
public class Invoice extends BaseEntity {

    @Column(name = "invoice_date")
    @Temporal(TemporalType.DATE)
    @HtmlTableColHeader(header = "Date", dateFormat = "dd/MM/yyy")
    @HtmlFormField(label = "Invoice Date", type = HtmlFormFieldType.DATE, required = true)
    private Date invoiceDate;

    @Column(name = "invoice_no")
    @HtmlTableColHeader(header = "Invoice Number")
    private String invoiceNo;

    @Column(name = "total")
    @HtmlTableColHeader(header = "Total", numberFormat = "#,###.##")
    @HtmlFormField(label = "Invoice Total", required = true)
    private BigDecimal total;

    @Column(name = "narration",columnDefinition = "text")
    @HtmlTableColHeader(header = "Narration")
    @HtmlFormField(label = "Narration", required = true)
    private String narration;

    @ManyToOne(fetch = FetchType.LAZY)
    private Journal journal;

    @HtmlTableColHeader(header = "Journal No")
    @Formula("(select j.journal_no from journals j where j.id=journal_id)")
    private String journalNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @HtmlFormField(label = "Customer ID", required = true, selectList = "Customer")
    @Formula("(customer_id)")
    private Long customerId;

    @HtmlTableColHeader(header = "Customer")
    @Formula("(select c.name from customers c where c.id=customer_id)")
    private String customerName;

   public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(String journalNo) {
        this.journalNo = journalNo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
