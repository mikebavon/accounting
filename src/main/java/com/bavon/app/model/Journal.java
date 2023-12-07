package com.bavon.app.model;

import com.bavon.app.view.helper.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Formula("(select sum(l.debit) from journal_lines l where l.journal_id=id)")
    //@HtmlTableColHeader(header = "Debit", numberFormat = "#,###.00")
    @HtmlFormField(label = "Debit Amount", type = HtmlFormFieldType.NUMBER)
    private BigDecimal debitBalance;

    @Formula("(select sum(l.credit) from journal_lines l where l.journal_id=id)")
    //@HtmlTableColHeader(header = "Credit", numberFormat = "#,###.00")
    @HtmlFormField(label = "Credit Amount", type = HtmlFormFieldType.NUMBER)
    private BigDecimal creditBalance;

    @OneToMany(mappedBy = "journal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JournalLine> journalLines = new ArrayList<>();

    @HtmlTableColHeader(header = "Entries")
    @Transient
    private String journalLineText;

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

    public List<JournalLine> getJournalLines() {
        return journalLines;
    }

    public void setJournalLines(List<JournalLine> journalLines) {
        this.journalLines = journalLines;
    }

    public void addJournalLine(JournalLine journalLine){
        journalLine.setJournal(this);
        getJournalLines().add(journalLine);
    }

    public void addJournalLines(List<JournalLine> journalLines){
        for (JournalLine journalLine : journalLines)
            addJournalLine(journalLine);
    }

    public String getJournalLineText() {
        return journalLineText;
    }

    public void setJournalLineText(String journalLineText) {
        this.journalLineText = journalLineText;
    }
}
