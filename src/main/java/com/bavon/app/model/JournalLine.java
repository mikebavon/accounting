package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlFormFieldType;
import com.bavon.app.view.helper.HtmlTable;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;

@HtmlTable(addUrl = "./journallines?action=add")
@HtmlForm(label = "Journal Entry", url = "./journallines")
@Entity
@Table(name = "journal_lines")
@DynamicUpdate
@DynamicInsert
public class JournalLine extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Journal journal;

    @HtmlFormField(label = "Journal", selectList = "journals", selectValue = "id", selectValueInSuper=true, selectDisplay = "displayInfo")
    @Formula("(journal_id)")
    private Long journalId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @HtmlFormField(label = "Account", selectList = "accounts", selectValue = "id", selectValueInSuper=true, selectDisplay = "name")
    @Formula("(account_id)")
    private Long accountId;

    @Formula("(select a.account_name from accounts a where a.id=account_id)")
    private String accountName;

    @HtmlFormField(label = "Particulars", required = true)
    @Column(nullable = false)
    private String narration;

    @HtmlFormField(label = "Debit Amount", type = HtmlFormFieldType.NUMBER)
    @Column
    private BigDecimal debit;

    @HtmlFormField(label = "Credit Amount", type = HtmlFormFieldType.NUMBER)
    @Column
    private BigDecimal credit;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

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
}
