package com.bavon.app.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "journal_lines")
@DynamicUpdate
@DynamicInsert
public class JournalLine extends BaseEntity{

    @Column(nullable = false)
    private String narration;

    @Column
    private BigDecimal debit;

    @Column
    private BigDecimal credit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Journal journal;

    @Formula("(journal_id)")
    private Long journalId;

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }
}
