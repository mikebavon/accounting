package com.bavon.app.model.entity;

import com.bavon.app.view.html.BavonHtmlForm;
import com.bavon.app.view.html.BavonHtmlFormField;
import com.bavon.app.view.html.BavonTableColHeader;

import java.io.Serializable;
import java.math.BigDecimal;

@BavonHtmlForm(label = "Customer", url = "./customers")
public class Customer implements Serializable {

    @BavonTableColHeader(header = "Customer Name")
    @BavonHtmlFormField(label = "Name")
    private String name;

    @BavonTableColHeader(header = "Customer Address")
    @BavonHtmlFormField(label = "Customer Address")
    private String address;

    @BavonTableColHeader(header = "Account Balance")
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
