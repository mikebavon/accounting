package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTable;
import com.bavon.app.view.helper.HtmlTableColHeader;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@HtmlTable(addUrl = "./accounts?action=add")
@HtmlForm(label = "Account", url = "./accounts")
public class Account extends BaseEntity {

    @Column(name = "code")
    @HtmlTableColHeader(header = "Account Code")
    @HtmlFormField(label = "Account Code", required = true)
    private String code;

    @Column(name = "account_name")
    @HtmlTableColHeader(header = "Account Name")
    @HtmlFormField(label = "Account Name", required = true)
    private String name;

    @Transient
    private BigDecimal balance;

    @Column(columnDefinition = "longtext")
    private String notes;

    @HtmlFormField(label = "Account Category", required = true)
    @Column
    @Enumerated(EnumType.STRING)
    private AccountCategory category;

    @HtmlFormField(label = "Account Type", required = true)
    @Column
    @Enumerated(EnumType.STRING)
    private AccountType type;

    public Account(){}

    public Account(String code, String name, BigDecimal balance){
        this.code = code;
        this.name = name;
        this.balance = balance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public AccountCategory getCategory() {
        return category;
    }

    public void setCategory(AccountCategory category) {
        this.category = category;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
