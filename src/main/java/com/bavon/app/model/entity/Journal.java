package com.bavon.app.model.entity;

import com.bavon.app.view.html.BavonHtmlForm;
import com.bavon.app.view.html.BavonHtmlFormField;
import com.bavon.app.view.html.BavonTableColHeader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@BavonHtmlForm(label = "Journal", url = "./journals")
public class Journal implements Serializable {

    @BavonTableColHeader(header = "Date")
    @BavonHtmlFormField(label = "Transaction Date")
    private Date date;

    @BavonTableColHeader(header = "Journal No")
    private String journalNo;

    @BavonTableColHeader(header = "Particulars")
    @BavonHtmlFormField(label = "Particulars")
    private String memo;

    @BavonTableColHeader(header = "Debit")
    @BavonHtmlFormField(label = "Debit Amount")
    private BigDecimal debitBalance;

    @BavonTableColHeader(header = "Credit")
    @BavonHtmlFormField(label = "Credit Amount")
    private BigDecimal creditBalance;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(String journalNo) {
        this.journalNo = journalNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(BigDecimal debitBalance) {
        this.debitBalance = debitBalance;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }
}
