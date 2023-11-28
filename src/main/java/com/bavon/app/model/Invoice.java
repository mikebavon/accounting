package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

import java.math.BigDecimal;
import java.util.Date;


@DbTable(name = "invoices")
@HtmlTable(addUrl = "./invoices?action=add")
@HtmlForm(label = "Invoice", url = "./invoices")
public class Invoice extends BaseEntity {

    @HtmlTableColHeader(header = "Date", dateFormat = "dd/MM/yyy")
    @HtmlFormField(label = "Invoice Date", type = HtmlFormFieldType.DATE, required = true)
    private Date invoiceDate;

    @DbTableColumn(name = "invoice_no")
    @HtmlTableColHeader(header = "Invoice Number")
    private String invoiceNo;

    @DbTableColumn(name = "total", definition = "decimal(10,2)")
    @HtmlTableColHeader(header = "Total", numberFormat = "#,###.##")
    @HtmlFormField(label = "Invoice Total", required = true)
    private BigDecimal total;

    @DbTableColumn(name = "narration")
    @HtmlTableColHeader(header = "Narration")
    @HtmlFormField(label = "Narration", required = true)
    private String narration;

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
}
