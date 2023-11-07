package com.bavon.app.model.entity;

import com.bavon.app.view.html.BavonHtmlForm;
import com.bavon.app.view.html.BavonHtmlFormField;
import com.bavon.app.view.html.BavonTableColHeader;

import java.io.Serializable;
import java.math.BigDecimal;

@BavonHtmlForm(label = "Account", url = "./account")
public class Account implements Serializable {

    @BavonTableColHeader(header = "Code Of The Account")
    @BavonHtmlFormField(label = "Account Code")
    private String code;

    @BavonTableColHeader(header = "Name Of The Account")
    @BavonHtmlFormField(label = "Account Name")
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
