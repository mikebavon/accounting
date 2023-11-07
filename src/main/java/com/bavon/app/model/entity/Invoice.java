package com.bavon.app.model.entity;

import com.bavon.app.view.html.BavonHtmlForm;
import com.bavon.app.view.html.BavonHtmlFormField;

import java.io.Serializable;
import java.util.Date;

@BavonHtmlForm(label = "Invoice", url = "./invoices", httpMethod = "POST")
public class Invoice implements Serializable {

    @BavonHtmlFormField(label = "Invoice Date")
    private Date invoiceDate;

    @BavonHtmlFormField(label = "Invoice Number")
    private String invoiceNo;

    private Journal journal;

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
}
