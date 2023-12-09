package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;



@NamedQueries({
    @NamedQuery(name = Invoice.InvoiceAbove1000, query = "FROM Invoice i WHERE i.total>1000"),
    @NamedQuery(name = Invoice.InvoiceBelow1000, query = "FROM Invoice i WHERE i.total<1000"),
    @NamedQuery(name = Invoice.TodaysInvoice, query = "FROM Invoice i WHERE i.invoiceDate=CURDATE()")
})
@Entity
@Table(name = "invoices")
@HtmlTable(addUrl = "./invoices?action=add")
@HtmlForm(label = "Invoice", url = "./invoices")
public class Invoice extends BaseEntity {

    public static final String InvoiceAbove1000 = "Invoice.InvoiceAbove1000";
    public static final String InvoiceBelow1000 = "Invoice.InvoiceBelow1000";
    public static final String TodaysInvoice = "Invoice.TodaysInvoice";

    @Column(name = "invoice_date")
    @Temporal(TemporalType.DATE)
    @HtmlTableColHeader(header = "Date", dateFormat = "dd/MM/yyy")
    @HtmlFormField(label = "Invoice Date", type = HtmlFormFieldType.DATE, required = true)
    private Date invoiceDate;

    @Column(name = "invoice_no")
    @HtmlTableColHeader(header = "Invoice Number")
    private String invoiceNo;

    @Column(name = "narration",columnDefinition = "text")
    @HtmlTableColHeader(header = "Narration")
    @HtmlFormField(label = "Narration", required = true)
    private String narration;

    @DecimalMin("1.0")
    @Column(name = "total")
    @HtmlTableColHeader(header = "Total", numberFormat = "#,###.##")
    @HtmlFormField(label = "Invoice Total", type = HtmlFormFieldType.NUMBER, required = true)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    private Journal journal;

    @HtmlTableColHeader(header = "Journal No")
    @Formula("(select j.journal_no from journals j where j.id=journal_id)")
    private String journalNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @HtmlFormField(label = "Customer", selectList = "customers", selectValue = "id", selectValueInSuper=true, selectDisplay = "name")
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

    @JsonIgnore
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

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @JsonIgnore
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
