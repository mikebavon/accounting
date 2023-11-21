package com.bavon.app.model;

import com.bavon.app.view.helper.HtmlForm;
import com.bavon.app.view.helper.HtmlFormField;
import com.bavon.app.view.helper.HtmlTable;
import com.bavon.app.view.helper.HtmlTableColHeader;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

import java.math.BigDecimal;

@DbTable(name = "items")
@HtmlTable(addUrl = "./items?action=add")
@HtmlForm(label = "Item", url = "./items")
public class Item extends BaseEntity{

    @DbTableColumn(name = "name")
    @HtmlFormField(label = "Item Name")
    @HtmlTableColHeader(header = "Item Name")
    private String name;

    @DbTableColumn(name = "description")
    @HtmlFormField(label = "Description")
    @HtmlTableColHeader(header = "Description")
    private String description;

    @DbTableColumn(name = "price", definition = "decimal(10,2)")
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

