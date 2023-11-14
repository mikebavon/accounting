package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTable;

import java.io.Serializable;
import java.util.Date;


@HtmlTable(addUrl = "./invoices?action=add")
@HtmlForm(label = "Invoice", url = "./invoices")
public class Invoice implements Serializable {

    @HtmlFormField(label = "Invoice Date")
    private Date invoiceDate;

    @HtmlFormField(label = "Invoice Number")
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
