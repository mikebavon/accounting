package com.bavon.app.model;

import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

@DbTable(name = "audit_logs")
public class AuditLog extends BaseEntity{

    @DbTableColumn(name = "log_details", definition = "longtext")
    private String logDetails;

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }
}
