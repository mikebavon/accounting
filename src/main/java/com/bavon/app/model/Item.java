package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTable;
import com.bavon.app.view.helper.HtmlTableColHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@HtmlTable(addUrl = "./items?action=add")
@HtmlForm(label = "Item", url = "./items")
public class Item extends BaseEntity{

    @Column(name = "name", nullable = false)
    @HtmlFormField(label = "Item Name")
    @HtmlTableColHeader(header = "Item Name")
    private String name;

    @Column(name = "description")
    @HtmlFormField(label = "Description")
    @HtmlTableColHeader(header = "Description")
    private String description;

    @Column(name = "price")
    @HtmlFormField(label = "Item Price")
    @HtmlTableColHeader(header = "Item Price")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

