package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTable;
import com.bavon.app.view.helper.HtmlTableColHeader;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

import java.io.Serializable;
import java.math.BigDecimal;

@DbTable(name = "accounts")
@HtmlTable(addUrl = "./accounts?action=add")
@HtmlForm(label = "Account", url = "./accounts")
public class Account implements Serializable {

    @DbTableColumn(name = "account_code")
    @HtmlTableColHeader(header = "Account Code")
    @HtmlFormField(label = "Account Code", required = true)
    private String code;

    @DbTableColumn(name = "account_name")
    @HtmlTableColHeader(header = "Account Name")
    @HtmlFormField(label = "Account Name", required = true)
    private String name;

    private BigDecimal balance;

    @DbTableColumn(name = "notes")
    private String notes;

    public Account(){

    }

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
}
