package com.bavon.app.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Account implements Serializable {

    private String code;

    private String name;

    private BigDecimal balance;

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

    public String tableRow(){

        StringBuilder trBuilder = new StringBuilder();

        trBuilder.append("<tr>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getCode())).append("</td>");
        trBuilder.append("<td>").append(StringUtils.trimToEmpty(getName())).append("</td>");
        trBuilder.append("<td>").append(getBalance() == null? ""
            : new DecimalFormat("#,###.##").format(getBalance())).append("</td>");
        trBuilder.append("<tr>");

        return trBuilder.toString();
    }
}
