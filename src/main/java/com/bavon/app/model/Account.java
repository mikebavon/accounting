package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTableColHeader;
import com.bavon.database.helper.DbColumn;
import com.bavon.database.helper.DbTable;

import java.io.Serializable;
import java.math.BigDecimal;

@DbTable(tableName = "journals")
@HtmlForm(label = "Account", url = "./account")
public class Account implements Serializable {

    @DbColumn(columnName = "code")
    @HtmlTableColHeader(header = "Code Of The Account")
    @HtmlFormField(label = "Account Code")
    private String code;

    @DbColumn(columnName = "name")
    @HtmlTableColHeader(header = "Name Of The Account")
    @HtmlFormField(label = "Account Name")
    private String name;

    private BigDecimal balance;

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
