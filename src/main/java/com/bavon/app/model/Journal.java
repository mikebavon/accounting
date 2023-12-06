package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "journals")
@HtmlTable(addUrl = "./journals?action=add")
@HtmlForm(label = "Journal", url = "./journals")
public class Journal extends BaseEntity {

    @HtmlTableColHeader(header = "Date", dateFormat = "dd MMM yyyy")
    @Column(name = "txn_date")
    @Temporal(TemporalType.DATE)
    @HtmlFormField(label = "Transaction Date", type = HtmlFormFieldType.DATE, required = true)
    private Date date;

    @Column(name = "journal_no", nullable = false, unique = true)
    @HtmlTableColHeader(header = "Journal No")
    private String journalNo;

    @Column(name = "particulars", columnDefinition = "longtext")
    @HtmlTableColHeader(header = "Particulars")
    @HtmlFormField(label = "Particulars", required = true)
    private String memo;

    @Column(name = "debit")
    @HtmlTableColHeader(header = "Debit", numberFormat = "#,###.00")
    @HtmlFormField(label = "Debit Amount", type = HtmlFormFieldType.NUMBER)
    private BigDecimal debitBalance;

    @Column(name = "credit")
    @HtmlTableColHeader(header = "Credit", numberFormat = "#,###.00")
    @HtmlFormField(label = "Credit Amount", type = HtmlFormFieldType.NUMBER)
    private BigDecimal creditBalance;

    @HtmlTableColHeader(header = "Net Balance", numberFormat = "#,###.00")
    @Formula("(coalesce(debit,0)-coalesce(credit,0))")
    private BigDecimal netBalance;

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

    public BigDecimal getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(BigDecimal netBalance) {
        this.netBalance = netBalance;
    }
}
