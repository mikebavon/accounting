package com.bavon.app.model;

import com.bavon.app.view.helper.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@HtmlTable(addUrl = "./journals?action=add")
@HtmlForm(label = "Journal", url = "./journals")
public class Journal implements Serializable {

    @HtmlTableColHeader(header = "Date")
    @HtmlFormField(label = "Transaction Date", type = HtmlFormFieldType.DATE)
    private Date date;

    @HtmlTableColHeader(header = "Journal No")
    private String journalNo;

    @HtmlTableColHeader(header = "Particulars")
    @HtmlFormField(label = "Particulars")
    private String memo;

    @HtmlTableColHeader(header = "Debit")
    @HtmlFormField(label = "Debit Amount", type = HtmlFormFieldType.NUMBER)
    private BigDecimal debitBalance;

    @HtmlTableColHeader(header = "Credit")
    @HtmlFormField(label = "Credit Amount", type = HtmlFormFieldType.NUMBER)
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
