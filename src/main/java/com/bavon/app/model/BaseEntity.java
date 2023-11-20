package com.bavon.app.model;

import com.bavon.database.helper.DbTableColumn;
import com.bavon.database.helper.DbTableId;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    @DbTableId
    @DbTableColumn(name = "id", definition = "int")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
