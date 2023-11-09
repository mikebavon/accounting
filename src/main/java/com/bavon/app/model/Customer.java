package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTableColHeader;

import java.io.Serializable;
import java.math.BigDecimal;

@HtmlForm(label = "Customer", url = "./customers")
public class Customer implements Serializable {

    @HtmlTableColHeader(header = "Customer Name")
    @HtmlFormField(label = "Name")
    private String name;

    @HtmlTableColHeader(header = "Customer Address")
    @HtmlFormField(label = "Customer Address")
    private String address;

    @HtmlTableColHeader(header = "Account Balance")
    private BigDecimal accountBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}