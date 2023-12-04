package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTable;
import com.bavon.app.view.helper.HtmlTableColHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "vendors")
@HtmlTable(addUrl = "./vendors?action=add")
@HtmlForm(label = "Vendor/Supplier", url = "./vendors")
public class Vendor extends BaseEntity {

    @Column(name = "vendor_name", nullable = false)
    @HtmlTableColHeader(header = "Vendor Name")
    @HtmlFormField(label = "Vendor Name", required = true)
    private String name;

    @Column(name = "vendor_address")
    @HtmlTableColHeader(header = "Vendor Address")
    @HtmlFormField(label = "Vendor Address", required = true)
    private String address;

    @Transient
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
